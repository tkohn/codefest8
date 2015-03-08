package de.codefest8.gamification8.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.games.Notifications;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.*;

import de.codefest8.gamification8.GlobalState;
import de.codefest8.gamification8.MainActivity;
import de.codefest8.gamification8.R;
import de.codefest8.gamification8.UserMessagesHandler;
import de.codefest8.gamification8.models.TripDTO;
import de.codefest8.gamification8.network.ResponseCallback;
import de.codefest8.gamification8.network.TripPointsResolver;


public class TrackDetailFragment extends Fragment  {
    private final static String LOG_TAG = "TrackDetailFragment";

    TripDTO trip;

    MapView mMapView;
    private GoogleMap googleMap;
    private MarkerOptions marker;
    private List<LatLng> points;
    private List<Map<String, Double>> properties;
    private Map<String, Pair<Double, Double>> propertiesMinMax;
    private List<String> propertyNames;

    private AlertDialog loadingDataDialog;

    Marker oldMarker = null;

    int i = 0;

    public TrackDetailFragment() {
        propertyNames = new ArrayList<>();
        propertyNames.add("gps_speed_kmh");
        propertyNames.add("engine_load");
        propertyNames.add("engine_rpm");
        propertyNames.add("air_temperature");
        propertyNames.add("fuel_level");
        propertyNames.add("kpl");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_trackdetail, container, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data);
        loadingDataDialog = builder.create();


        this.trip = GlobalState.getInstance().getTrip();

        setHasOptionsMenu(true);

        loadData();

        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_track, menu);
        menu.findItem(R.id.action_save_raw).setVisible(true);
        menu.findItem(R.id.action_share).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId())
        {
            case R.id.action_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Sharing my AixCruise trip with you #codeFEST8");
                sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + getActivity().getExternalFilesDir(null).getAbsolutePath() + "/dump/mapdump.png"));
                sendIntent.setType("image/*");
                startActivity(Intent.createChooser(sendIntent, "Share your AixCruise"));
                break;
            case R.id.action_save_raw:
                // todo
                break;
            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Warning");
                builder.setMessage("Do you want to delete the trip with id '" + trip.getId() + "'?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Trip with id '" + trip.getId() + "' deleted!", Toast.LENGTH_SHORT).show();
                        ((MainActivity)getActivity()).goToFragment(FragmentType.TrackHistory);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
                break;
        }
        return true;
    }
    
    private class TripPointsResponseCallback implements ResponseCallback {
        @Override
        public void success(JSONObject response) {

        }

        @Override
        public void successArray(JSONArray response) {
            points = new ArrayList<LatLng>(response.length());
            try {
                properties = new ArrayList<>();
                propertiesMinMax = new HashMap<>();
                for(int i = 0; i < propertyNames.size(); i++)
                {
                    propertiesMinMax.put(propertyNames.get(i), new Pair<>(Double.MAX_VALUE, Double.MIN_VALUE));
                }

                for (int i = 0; i < response.length(); i++) {
                    JSONArray arr = response.getJSONArray(i);
                    points.add(new LatLng(arr.getDouble(1), arr.getDouble(0)));

                    Map tempMap = new HashMap<String, Double>();
                    for(int j = 0; j < propertyNames.size(); j++)
                    {
                        int index = j+2; // shift because of prepended long and lat
                        double tmpValue = arr.getDouble(index);
                        tempMap.put(propertyNames.get(j), tmpValue);
                        if(tmpValue < propertiesMinMax.get(propertyNames.get(j)).first)
                        {
                            propertiesMinMax.put(propertyNames.get(j),  new Pair<>(tmpValue, propertiesMinMax.get(propertyNames.get(j)).second));
                        }
                        if(tmpValue > propertiesMinMax.get(propertyNames.get(j)).second)
                        {
                            propertiesMinMax.put(propertyNames.get(j),  new Pair<>(propertiesMinMax.get(propertyNames.get(j)).first, tmpValue));
                        }
                    }

                    properties.add(tempMap);
                }

            } catch (JSONException ex) {
                UserMessagesHandler.getInstance().registerError("Error while parsing achievements list response.");
                Log.e(LOG_TAG, ex.toString());
            }
            loadingDataDialog.dismiss();
            startMap();
        }

        @Override
        public void fail(int code, String message) {
            loadingDataDialog.dismiss();
            UserMessagesHandler.getInstance().registerError("Could not load points array.");
        }
    }

    private void loadData() {
        loadingDataDialog.show();
        TripPointsResolver resolver = new TripPointsResolver(new TripPointsResponseCallback(), GlobalState.getInstance().getTrip());
        resolver.doRequestArray();
    }

    private void startMap() {
        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();

        if(points.isEmpty())
        {
            Log.e("FATAL ERROR", "NO POINTS FOUND");
            return;
        }

        // create marker
        marker = new MarkerOptions().position(points.get(0)).title("Trip Start");
        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        // adding marker
        googleMap.addMarker(marker);

        // create marker
        marker = new MarkerOptions().position(points.get(points.size()-1)).title("Trip End");
        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        // adding marker
        googleMap.addMarker(marker);

        int distance = 5;
        int activeProperty = 0;
        for(int c = 0; c < points.size()-distance; c+=distance) {
            double value = 0;
            for(int i = 0; i < distance; i++)
            {
                int index = c+i;
                value += properties.get(index).get(propertyNames.get(activeProperty));
            }
            value /= distance;

            float[] hsv = new float[3];
            hsv[0] = ((float)(value - propertiesMinMax.get(propertyNames.get(activeProperty)).first )/
                    (float)(propertiesMinMax.get(propertyNames.get(activeProperty)).second - propertiesMinMax.get(propertyNames.get(activeProperty)).first))*360.0f;
            hsv[1] = 1.0f;
            hsv[2] = 0.9f;
            int color = new Color().HSVToColor(hsv);
            Log.i("Color", String.valueOf(color));

            PolylineOptions multiPoint = new PolylineOptions().color(color);
            int i = c > 0 ? -1 : 0;
            for(; i+c < points.size(); i++)
            {
                int id = c + i;
                multiPoint.add(points.get(id));
            }
            googleMap.addPolyline(multiPoint);
        }

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                googleMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
                    @Override
                    public void onSnapshotReady(Bitmap bitmap) {
                        try
                        {
                            File path = new File(getActivity().getExternalFilesDir(null).getAbsolutePath() + "/dump");
                            Log.i("AixCruise", path.getAbsolutePath());
                            if(!path.exists()) path.mkdirs();
                            File file = new File(path, "mapdump.png");
                            FileOutputStream stream = new FileOutputStream(file, false);
                            Log.i("AixCruise", file.getAbsolutePath());
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            stream.flush();
                            stream.close();
                        }
                        catch (Exception ex)
                        {
                            Log.e("AixCruise", ex.getMessage());
                        }
                    }
                });
            }
        });

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng ll : points)
        {
            builder.include(ll);
        }
        CameraUpdate initialUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), 20);

        googleMap.moveCamera(initialUpdate);

        /*

        googleMap.animateCamera(initialUpdate, 1000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                googleMap.snapshot(new GoogleMap.SnapshotReadyCallback() {
                    @Override
                    public void onSnapshotReady(Bitmap bitmap) {

                        final Timer t = new Timer("updateTimer");
                        t.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {
                                if (getActivity() != null) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            i++;

                                            if (i < points.size() - 1) {
                                                LatLng cur = points.get(i);
                                                LatLng nxt = points.get(i + 1);
                                                Float heading = (float) computeHeading(cur, nxt);
                                                CameraPosition pos = new CameraPosition.Builder().target(cur).bearing(heading).tilt(45).zoom(16).build();
                                                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(pos), 200, null);
                                                if (oldMarker != null) {
                                                    oldMarker.remove();
                                                }

                                                MarkerOptions newMarker = new MarkerOptions()
                                                        .position(cur)
                                                        .title("Current Position")
                                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.navi))
                                                        .anchor(0.5f, 3.0f / 5.0f)
                                                        .rotation(1.0f / heading);
                                                oldMarker = googleMap.addMarker(newMarker);
                                            } else {
                                                t.cancel();
                                            }
                                        }
                                    });
                                }
                            }
                        }, 100, 200);
                    }
                });
            }

            @Override
            public void onCancel() {

            }
        });*/
    }

    /**
     * Returns the heading from one LatLng to another LatLng. Headings are
     * expressed in degrees clockwise from North within the range [-180,180).
     * @return The heading in degrees clockwise from north.
     */
    public static double computeHeading(LatLng from, LatLng to) {
// http://williams.best.vwh.net/avform.htm#Crs
        double fromLat = toRadians(from.latitude);
        double fromLng = toRadians(from.longitude);
        double toLat = toRadians(to.latitude);
        double toLng = toRadians(to.longitude);
        double dLng = toLng - fromLng;
        double heading = Math.atan2(
                Math.sin(dLng) * Math.cos(toLat),
                Math.cos(fromLat) * Math.sin(toLat) - Math.sin(fromLat) * Math.cos(toLat) * Math.cos(dLng));
        return wrap(toDegrees(heading), -180, 180);
    }

    /**
     * Wraps the given value into the inclusive-exclusive interval between min and max.
     * @param n The value to wrap.
     * @param min The minimum.
     * @param max The maximum.
     */
    static double wrap(double n, double min, double max) {
        return (n >= min && n < max) ? n : (mod(n - min, max - min) + min);
    }

    /**
     * Returns the non-negative remainder of x / m.
     * @param x The operand.
     * @param m The modulus.
     */
    static double mod(double x, double m) {
        return ((x % m) + m) % m;
    }

    private Location convertLatLngToLocation(LatLng latLng) {
        Location location = new Location("helpLoc");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);
        return location;
    }

    private float bearingBetweenLatLngs(LatLng beginLatLng,LatLng endLatLng) {
        Location beginLocation = convertLatLngToLocation(beginLatLng);
        Location endLocation = convertLatLngToLocation(endLatLng);
        return beginLocation.bearingTo(endLocation);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}