package de.codefest8.gamification8.fragments;

import android.app.AlertDialog;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Math.*;

import de.codefest8.gamification8.GlobalState;
import de.codefest8.gamification8.R;
import de.codefest8.gamification8.UserMessagesHandler;
import de.codefest8.gamification8.models.AchievementDTO;
import de.codefest8.gamification8.network.AchievementsResolver;
import de.codefest8.gamification8.network.ResponseCallback;
import de.codefest8.gamification8.network.TripPointsResolver;


public class TrackDetailFragment extends Fragment  {
    private final static String LOG_TAG = "TrackDetailFragment";
    MapView mMapView;
    private GoogleMap googleMap;
    private MarkerOptions marker;
    private List<LatLng> points;
    private List<Map<String, Double>> properties;

    private AlertDialog loadingDataDialog;

    Marker oldMarker = null;

    int i = 0;

    public TrackDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_trackdetail, container,
                false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_loading_data).setTitle(R.string.dialog_loading_data);
        loadingDataDialog = builder.create();

        loadData();

        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        return v;
    }

    private class TripPointsResponseCallback implements ResponseCallback {
        @Override
        public void success(JSONObject response) {

        }

        @Override
        public void successArray(JSONArray response) {
            points = new ArrayList<LatLng>(response.length());
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONArray arr = response.getJSONArray(i);
                    points.add(new LatLng(arr.getDouble(1), arr.getDouble(0)));
                    //Map
                    //properties.add();
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

    private void animateTo(LatLng currentPosition, double zoom, double bearing, double tilt, final int milliseconds) {

        if (googleMap==null) return;
        //googleMap.setMapType(paramMapMode);
        //mCurrentPosition=new LatLng(lat,lon);

        // animate camera jumps too much
        // so we set the camera instantly to the next point

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(currentPosition,(float)zoom, (float)tilt, (float)bearing)));

        // give Android a break so it can load tiles. If I start the animation
        // without pause, no tile loading is done

        mMapView.postDelayed(new Runnable(){
            @Override
            public void run() {
                // keeping numbers small you get a nice scrolling effect
                googleMap.animateCamera(CameraUpdateFactory.scrollBy(250-(float)Math.random()*500-250, 250-(float)Math.random()*500),milliseconds,null);

            }},500);
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
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        // adding marker
        googleMap.addMarker(marker);

        int distance = 5;
        for(int c = 0; c < points.size()-distance; c+=distance) {
            float[] hsv = new float[3];
            hsv[0] = ((float)(c-0)/(float)(points.size()-0))*360.0f;
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

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(points.get(0)).zoom(14).build();


        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 100, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {
                final Timer t = new Timer("updateTimer");
                t.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if(getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    i++;

                                    if (i < points.size() - 1) {
                                        LatLng cur = points.get(i);
                                        LatLng nxt = points.get(i + 1);
                                        Float heading = (float) computeHeading(cur, nxt);
                                        CameraPosition pos = new CameraPosition.Builder().target(cur).bearing(heading).tilt(45).zoom(16).build();
                                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(pos));
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
                }, 1000, 200);
            }

            @Override
            public void onCancel() {

            }
        });
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