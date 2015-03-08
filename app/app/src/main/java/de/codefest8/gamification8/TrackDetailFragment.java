package de.codefest8.gamification8;

import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.PolylineOptions;


import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

import java.util.List;


/**
 * Created by koerfer on 07.03.2015.

public class TrackDetailFragment extends Fragment {

    private MapFragment mapFragment;
    private GoogleMap googleMap;
    private List<Marker> markers;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trackdetail, container, false);
        return v;
    }

    public void addMarkerToMap(LatLng latLng) {
        Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng)
                .title("Trip")
                .snippet("snippet"));
        markers.add(marker);
    }

    public void init()
    {
        mapFragment = (MapFragment) getChildFragmentManager().getFragments().get(0).findFragmentById(R.id.map);
        googleMap = mapFragment.getMap();
        googleMap.setMyLocationEnabled(true);
    }
}
*/

public class TrackDetailFragment extends Fragment  {

    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_trackdetail, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();
        // latitude and longitude
        double latitude = 50.77738953;
        double longitude = 6.0506326;

        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title("Trip Start");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        googleMap.addMarker(marker);

        PolylineOptions rectOptions = new PolylineOptions();
        rectOptions.add(new LatLng(50.77738953, 6.0506326));
        rectOptions.add(new LatLng(50.77748455, 6.05066935));
        rectOptions.add(new LatLng(50.77759125, 6.05071736));
        rectOptions.add(new LatLng(50.77768079, 6.05072467));
        rectOptions.add(new LatLng(50.77779028, 6.05074427));
        rectOptions.add(new LatLng(50.7778716, 6.05074275));
        rectOptions.add(new LatLng(50.77796281, 6.05073447));
        rectOptions.add(new LatLng(50.77806174, 6.05073929));
        rectOptions.add(new LatLng(50.77814907, 6.05073025));
        rectOptions.add(new LatLng(50.77823997, 6.05072323));
        rectOptions.add(new LatLng(50.77834014, 6.0507206));
        rectOptions.add(new LatLng(50.77843901, 6.05072018));
        rectOptions.add(new LatLng(50.77851802, 6.05072098));
        rectOptions.add(new LatLng(50.77860839, 6.0507293));
        rectOptions.add(new LatLng(50.77870267, 6.05073978));
        rectOptions.add(new LatLng(50.77880624, 6.0507502));
        rectOptions.add(new LatLng(50.77890008, 6.05075494));
        rectOptions.add(new LatLng(50.778999, 6.05075833));
        rectOptions.add(new LatLng(50.77909419, 6.05076096));
        rectOptions.add(new LatLng(50.77919114, 6.050766));
        rectOptions.add(new LatLng(50.77928842, 6.05075884));
        rectOptions.add(new LatLng(50.77937207, 6.05077311));
        rectOptions.add(new LatLng(50.77944443, 6.05075204));
        rectOptions.add(new LatLng(50.77951876, 6.05070456));
        rectOptions.add(new LatLng(50.77957222, 6.05063038));
        rectOptions.add(new LatLng(50.77961196, 6.05052134));
        rectOptions.add(new LatLng(50.7796473, 6.05038746));
        rectOptions.add(new LatLng(50.7796565, 6.05024479));
        rectOptions.add(new LatLng(50.77966921, 6.05010021));
        rectOptions.add(new LatLng(50.77968711, 6.04997215));
        rectOptions.add(new LatLng(50.77969947, 6.04983225));
        rectOptions.add(new LatLng(50.77971711, 6.04967865));
        rectOptions.add(new LatLng(50.77972671, 6.0495379));
        rectOptions.add(new LatLng(50.7797421, 6.04938855));
        rectOptions.add(new LatLng(50.77977906, 6.04923104));
        rectOptions.add(new LatLng(50.77980224, 6.0490869));
        rectOptions.add(new LatLng(50.7798364, 6.04894165));
        rectOptions.add(new LatLng(50.77987177, 6.04880086));
        rectOptions.add(new LatLng(50.77990618, 6.04866036));
        rectOptions.add(new LatLng(50.77994676, 6.04851869));
        rectOptions.add(new LatLng(50.77998391, 6.04840211));
        rectOptions.add(new LatLng(50.78002871, 6.04827382));
        rectOptions.add(new LatLng(50.78007737, 6.04815162));
        rectOptions.add(new LatLng(50.78012515, 6.04802826));
        rectOptions.add(new LatLng(50.78017516, 6.04789351));
        rectOptions.add(new LatLng(50.78021959, 6.04777573));
        rectOptions.add(new LatLng(50.7802697, 6.04765921));
        rectOptions.add(new LatLng(50.78032276, 6.04753061));
        rectOptions.add(new LatLng(50.78036767, 6.04741817));
        rectOptions.add(new LatLng(50.78042238, 6.04729628));
        rectOptions.add(new LatLng(50.78047665, 6.04718832));
        rectOptions.add(new LatLng(50.78052321, 6.04706797));
        rectOptions.add(new LatLng(50.78058614, 6.04695197));
        rectOptions.add(new LatLng(50.78063954, 6.0468551));
        rectOptions.add(new LatLng(50.78069815, 6.04675694));
        rectOptions.add(new LatLng(50.78076309, 6.04665112));
        rectOptions.add(new LatLng(50.78081907, 6.04655938));
        rectOptions.add(new LatLng(50.78088234, 6.04646374));
        rectOptions.add(new LatLng(50.78094952, 6.04636542));
        rectOptions.add(new LatLng(50.7810221, 6.04626881));
        rectOptions.add(new LatLng(50.78109118, 6.04617696));
        rectOptions.add(new LatLng(50.78115401, 6.04610577));
        rectOptions.add(new LatLng(50.78121276, 6.04602175));
        rectOptions.add(new LatLng(50.78128409, 6.04594667));
        rectOptions.add(new LatLng(50.78134257, 6.04588171));
        rectOptions.add(new LatLng(50.78140665, 6.045801));
        rectOptions.add(new LatLng(50.78147374, 6.04572841));
        rectOptions.add(new LatLng(50.78153384, 6.04565646));
        rectOptions.add(new LatLng(50.78160923, 6.04557716));
        rectOptions.add(new LatLng(50.78167009, 6.0455155));
        rectOptions.add(new LatLng(50.78174119, 6.04544731));
        rectOptions.add(new LatLng(50.78181188, 6.04537155));
        rectOptions.add(new LatLng(50.78187393, 6.04531003));
        rectOptions.add(new LatLng(50.78194238, 6.04524931));
        rectOptions.add(new LatLng(50.78200873, 6.04518369));
        rectOptions.add(new LatLng(50.78209874, 6.04510395));
        rectOptions.add(new LatLng(50.78217778, 6.04502839));
        rectOptions.add(new LatLng(50.78224999, 6.04497304));
        rectOptions.add(new LatLng(50.78232635, 6.04491112));
        rectOptions.add(new LatLng(50.78240957, 6.04484291));
        rectOptions.add(new LatLng(50.78248179, 6.04478581));
        rectOptions.add(new LatLng(50.78256175, 6.04472626));
        rectOptions.add(new LatLng(50.78264221, 6.04466993));
        rectOptions.add(new LatLng(50.7827259, 6.04461159));
        rectOptions.add(new LatLng(50.78281521, 6.04455336));
        rectOptions.add(new LatLng(50.78289458, 6.04450303));
        rectOptions.add(new LatLng(50.78297918, 6.04444644));
        rectOptions.add(new LatLng(50.78307173, 6.0443837));
        rectOptions.add(new LatLng(50.78315752, 6.04433051));
        rectOptions.add(new LatLng(50.78324828, 6.04427755));
        rectOptions.add(new LatLng(50.78333571, 6.0442263));
        rectOptions.add(new LatLng(50.78342868, 6.04416945));
        rectOptions.add(new LatLng(50.78351939, 6.04412465));
        rectOptions.add(new LatLng(50.78360381, 6.04408896));
        rectOptions.add(new LatLng(50.78370917, 6.04404939));
        rectOptions.add(new LatLng(50.78380874, 6.04400667));
        rectOptions.add(new LatLng(50.78389342, 6.04396769));
        rectOptions.add(new LatLng(50.78397928, 6.04393217));
        rectOptions.add(new LatLng(50.784061, 6.04389806));
        rectOptions.add(new LatLng(50.78415151, 6.0438506));
        rectOptions.add(new LatLng(50.78425068, 6.04379958));
        rectOptions.add(new LatLng(50.78433564, 6.04378227));
        rectOptions.add(new LatLng(50.78443054, 6.04375223));
        rectOptions.add(new LatLng(50.78452728, 6.04371919));
        rectOptions.add(new LatLng(50.78461163, 6.04369291));
        rectOptions.add(new LatLng(50.78470466, 6.04367111));
        rectOptions.add(new LatLng(50.78480164, 6.04367165));
        rectOptions.add(new LatLng(50.78489673, 6.04366708));
        rectOptions.add(new LatLng(50.78500635, 6.0436563));
        rectOptions.add(new LatLng(50.78509705, 6.04364559));
        rectOptions.add(new LatLng(50.78519208, 6.0436395));
        rectOptions.add(new LatLng(50.78529192, 6.0436362));
        rectOptions.add(new LatLng(50.78538606, 6.04363644));
        rectOptions.add(new LatLng(50.78547749, 6.04364206));
        rectOptions.add(new LatLng(50.78557136, 6.04365061));
        rectOptions.add(new LatLng(50.7856604, 6.04365994));
        rectOptions.add(new LatLng(50.7857527, 6.04368475));
        rectOptions.add(new LatLng(50.78582969, 6.04370724));
        rectOptions.add(new LatLng(50.78591417, 6.04373768));
        rectOptions.add(new LatLng(50.78600132, 6.04377083));
        rectOptions.add(new LatLng(50.78608195, 6.04380239));
        rectOptions.add(new LatLng(50.78616606, 6.04383797));
        rectOptions.add(new LatLng(50.78625769, 6.04386483));
        rectOptions.add(new LatLng(50.78634337, 6.04389628));
        rectOptions.add(new LatLng(50.78642712, 6.04395393));
        rectOptions.add(new LatLng(50.78650458, 6.04400529));
        rectOptions.add(new LatLng(50.78658832, 6.04406353));
        rectOptions.add(new LatLng(50.78667035, 6.04412682));
        rectOptions.add(new LatLng(50.78675086, 6.04418285));
        rectOptions.add(new LatLng(50.78683293, 6.04425392));
        rectOptions.add(new LatLng(50.78691759, 6.04434427));
        rectOptions.add(new LatLng(50.7869987, 6.0444214));
        rectOptions.add(new LatLng(50.78709728, 6.0445227));
        rectOptions.add(new LatLng(50.78716944, 6.04460643));
        rectOptions.add(new LatLng(50.78724709, 6.04470408));
        rectOptions.add(new LatLng(50.78731749, 6.04481995));
        rectOptions.add(new LatLng(50.78738308, 6.04492224));
        rectOptions.add(new LatLng(50.78745515, 6.04503181));
        rectOptions.add(new LatLng(50.78751302, 6.04517063));
        rectOptions.add(new LatLng(50.78757631, 6.04530016));
        rectOptions.add(new LatLng(50.78764819, 6.04544033));
        rectOptions.add(new LatLng(50.78769653, 6.04557971));
        rectOptions.add(new LatLng(50.78774016, 6.04572111));
        rectOptions.add(new LatLng(50.7877671, 6.04589908));
        rectOptions.add(new LatLng(50.78779041, 6.04605571));
        rectOptions.add(new LatLng(50.78781998, 6.04622246));
        rectOptions.add(new LatLng(50.78783282, 6.04636695));
        rectOptions.add(new LatLng(50.78784104, 6.04652263));
        rectOptions.add(new LatLng(50.78785756, 6.04668236));
        rectOptions.add(new LatLng(50.78786562, 6.04686001));
        rectOptions.add(new LatLng(50.78787264, 6.04702941));
        rectOptions.add(new LatLng(50.78788742, 6.04720567));
        rectOptions.add(new LatLng(50.78789277, 6.04735055));
        rectOptions.add(new LatLng(50.78789755, 6.04751674));
        rectOptions.add(new LatLng(50.78789135, 6.04768727));
        rectOptions.add(new LatLng(50.78788903, 6.0478431));
        rectOptions.add(new LatLng(50.78788308, 6.04800633));
        rectOptions.add(new LatLng(50.78789213, 6.04816357));
        rectOptions.add(new LatLng(50.78788432, 6.04831662));
        rectOptions.add(new LatLng(50.78788021, 6.04848551));
        rectOptions.add(new LatLng(50.78786157, 6.04864195));
        rectOptions.add(new LatLng(50.78784871, 6.04879583));
        rectOptions.add(new LatLng(50.78782719, 6.04895887));
        rectOptions.add(new LatLng(50.78781177, 6.04911677));
        rectOptions.add(new LatLng(50.7877836, 6.0492809));
        rectOptions.add(new LatLng(50.78775634, 6.0494309));
        rectOptions.add(new LatLng(50.78772888, 6.04959274));
        rectOptions.add(new LatLng(50.78769875, 6.04974502));
        rectOptions.add(new LatLng(50.78766636, 6.0498856));
        rectOptions.add(new LatLng(50.78763247, 6.05002559));
        rectOptions.add(new LatLng(50.78759589, 6.05014959));
        rectOptions.add(new LatLng(50.78753773, 6.05022281));
        rectOptions.add(new LatLng(50.78747681, 6.05030831));
        rectOptions.add(new LatLng(50.78742774, 6.05039022));
        rectOptions.add(new LatLng(50.78741196, 6.05050394));
        rectOptions.add(new LatLng(50.7874271, 6.05062103));
        rectOptions.add(new LatLng(50.78745836, 6.05070175));
        rectOptions.add(new LatLng(50.78750393, 6.05072712));
        rectOptions.add(new LatLng(50.78759537, 6.05063241));
        rectOptions.add(new LatLng(50.78762775, 6.05052314));
        rectOptions.add(new LatLng(50.78760919, 6.05038816));
        rectOptions.add(new LatLng(50.7875818, 6.05029096));
        rectOptions.add(new LatLng(50.78750909, 6.05020982));
        rectOptions.add(new LatLng(50.7874446, 6.05019029));
        rectOptions.add(new LatLng(50.78741148, 6.05049294));
        rectOptions.add(new LatLng(50.78742124, 6.05061311));
        rectOptions.add(new LatLng(50.7874645, 6.05070557));
        rectOptions.add(new LatLng(50.7874871, 6.05076133));
        rectOptions.add(new LatLng(50.78756668, 6.05054282));
        rectOptions.add(new LatLng(50.78755773, 6.05041689));
        rectOptions.add(new LatLng(50.7874837, 6.05032946));
        rectOptions.add(new LatLng(50.78744165, 6.05024516));
        rectOptions.add(new LatLng(50.78739249, 6.05025623));
        rectOptions.add(new LatLng(50.78731422, 6.05036338));
        rectOptions.add(new LatLng(50.78716694, 6.05041933));
        rectOptions.add(new LatLng(50.78703136, 6.05044984));
        rectOptions.add(new LatLng(50.78688936, 6.05044734));
        rectOptions.add(new LatLng(50.78676088, 6.05046381));
        rectOptions.add(new LatLng(50.78662562, 6.05047483));
        rectOptions.add(new LatLng(50.78648912, 6.05046934));

        googleMap.addPolyline(rectOptions);


        List<LatLng> points = new ArrayList<>();

        points.add(new LatLng(50.77738953, 6.0506326));
        points.add(new LatLng(50.77748455, 6.05066935));
        points.add(new LatLng(50.77759125, 6.05071736));
        points.add(new LatLng(50.77768079, 6.05072467));
        points.add(new LatLng(50.77779028, 6.05074427));
        points.add(new LatLng(50.7778716, 6.05074275));
        points.add(new LatLng(50.77796281, 6.05073447));
        points.add(new LatLng(50.77806174, 6.05073929));
        points.add(new LatLng(50.77814907, 6.05073025));
        points.add(new LatLng(50.77823997, 6.05072323));
        points.add(new LatLng(50.77834014, 6.0507206));
        points.add(new LatLng(50.77843901, 6.05072018));
        points.add(new LatLng(50.77851802, 6.05072098));
        points.add(new LatLng(50.77860839, 6.0507293));
        points.add(new LatLng(50.77870267, 6.05073978));
        points.add(new LatLng(50.77880624, 6.0507502));
        points.add(new LatLng(50.77890008, 6.05075494));
        points.add(new LatLng(50.778999, 6.05075833));
        points.add(new LatLng(50.77909419, 6.05076096));
        points.add(new LatLng(50.77919114, 6.050766));
        points.add(new LatLng(50.77928842, 6.05075884));
        points.add(new LatLng(50.77937207, 6.05077311));
        points.add(new LatLng(50.77944443, 6.05075204));
        points.add(new LatLng(50.77951876, 6.05070456));
        points.add(new LatLng(50.77957222, 6.05063038));
        points.add(new LatLng(50.77961196, 6.05052134));
        points.add(new LatLng(50.7796473, 6.05038746));
        points.add(new LatLng(50.7796565, 6.05024479));
        points.add(new LatLng(50.77966921, 6.05010021));
        points.add(new LatLng(50.77968711, 6.04997215));
        points.add(new LatLng(50.77969947, 6.04983225));
        points.add(new LatLng(50.77971711, 6.04967865));
        points.add(new LatLng(50.77972671, 6.0495379));
        points.add(new LatLng(50.7797421, 6.04938855));
        points.add(new LatLng(50.77977906, 6.04923104));
        points.add(new LatLng(50.77980224, 6.0490869));
        points.add(new LatLng(50.7798364, 6.04894165));
        points.add(new LatLng(50.77987177, 6.04880086));
        points.add(new LatLng(50.77990618, 6.04866036));
        points.add(new LatLng(50.77994676, 6.04851869));
        points.add(new LatLng(50.77998391, 6.04840211));
        points.add(new LatLng(50.78002871, 6.04827382));
        points.add(new LatLng(50.78007737, 6.04815162));
        points.add(new LatLng(50.78012515, 6.04802826));
        points.add(new LatLng(50.78017516, 6.04789351));
        points.add(new LatLng(50.78021959, 6.04777573));
        points.add(new LatLng(50.7802697, 6.04765921));
        points.add(new LatLng(50.78032276, 6.04753061));
        points.add(new LatLng(50.78036767, 6.04741817));
        points.add(new LatLng(50.78042238, 6.04729628));
        points.add(new LatLng(50.78047665, 6.04718832));
        points.add(new LatLng(50.78052321, 6.04706797));
        points.add(new LatLng(50.78058614, 6.04695197));
        points.add(new LatLng(50.78063954, 6.0468551));
        points.add(new LatLng(50.78069815, 6.04675694));
        points.add(new LatLng(50.78076309, 6.04665112));
        points.add(new LatLng(50.78081907, 6.04655938));
        points.add(new LatLng(50.78088234, 6.04646374));
        points.add(new LatLng(50.78094952, 6.04636542));
        points.add(new LatLng(50.7810221, 6.04626881));
        points.add(new LatLng(50.78109118, 6.04617696));
        points.add(new LatLng(50.78115401, 6.04610577));
        points.add(new LatLng(50.78121276, 6.04602175));
        points.add(new LatLng(50.78128409, 6.04594667));
        points.add(new LatLng(50.78134257, 6.04588171));
        points.add(new LatLng(50.78140665, 6.045801));
        points.add(new LatLng(50.78147374, 6.04572841));
        points.add(new LatLng(50.78153384, 6.04565646));
        points.add(new LatLng(50.78160923, 6.04557716));
        points.add(new LatLng(50.78167009, 6.0455155));
        points.add(new LatLng(50.78174119, 6.04544731));
        points.add(new LatLng(50.78181188, 6.04537155));
        points.add(new LatLng(50.78187393, 6.04531003));
        points.add(new LatLng(50.78194238, 6.04524931));
        points.add(new LatLng(50.78200873, 6.04518369));
        points.add(new LatLng(50.78209874, 6.04510395));
        points.add(new LatLng(50.78217778, 6.04502839));
        points.add(new LatLng(50.78224999, 6.04497304));
        points.add(new LatLng(50.78232635, 6.04491112));
        points.add(new LatLng(50.78240957, 6.04484291));
        points.add(new LatLng(50.78248179, 6.04478581));
        points.add(new LatLng(50.78256175, 6.04472626));
        points.add(new LatLng(50.78264221, 6.04466993));
        points.add(new LatLng(50.7827259, 6.04461159));
        points.add(new LatLng(50.78281521, 6.04455336));
        points.add(new LatLng(50.78289458, 6.04450303));
        points.add(new LatLng(50.78297918, 6.04444644));
        points.add(new LatLng(50.78307173, 6.0443837));
        points.add(new LatLng(50.78315752, 6.04433051));
        points.add(new LatLng(50.78324828, 6.04427755));
        points.add(new LatLng(50.78333571, 6.0442263));
        points.add(new LatLng(50.78342868, 6.04416945));
        points.add(new LatLng(50.78351939, 6.04412465));
        points.add(new LatLng(50.78360381, 6.04408896));
        points.add(new LatLng(50.78370917, 6.04404939));
        points.add(new LatLng(50.78380874, 6.04400667));
        points.add(new LatLng(50.78389342, 6.04396769));
        points.add(new LatLng(50.78397928, 6.04393217));
        points.add(new LatLng(50.784061, 6.04389806));
        points.add(new LatLng(50.78415151, 6.0438506));
        points.add(new LatLng(50.78425068, 6.04379958));
        points.add(new LatLng(50.78433564, 6.04378227));
        points.add(new LatLng(50.78443054, 6.04375223));
        points.add(new LatLng(50.78452728, 6.04371919));
        points.add(new LatLng(50.78461163, 6.04369291));
        points.add(new LatLng(50.78470466, 6.04367111));
        points.add(new LatLng(50.78480164, 6.04367165));
        points.add(new LatLng(50.78489673, 6.04366708));
        points.add(new LatLng(50.78500635, 6.0436563));
        points.add(new LatLng(50.78509705, 6.04364559));
        points.add(new LatLng(50.78519208, 6.0436395));
        points.add(new LatLng(50.78529192, 6.0436362));
        points.add(new LatLng(50.78538606, 6.04363644));
        points.add(new LatLng(50.78547749, 6.04364206));
        points.add(new LatLng(50.78557136, 6.04365061));
        points.add(new LatLng(50.7856604, 6.04365994));
        points.add(new LatLng(50.7857527, 6.04368475));
        points.add(new LatLng(50.78582969, 6.04370724));
        points.add(new LatLng(50.78591417, 6.04373768));
        points.add(new LatLng(50.78600132, 6.04377083));
        points.add(new LatLng(50.78608195, 6.04380239));
        points.add(new LatLng(50.78616606, 6.04383797));
        points.add(new LatLng(50.78625769, 6.04386483));
        points.add(new LatLng(50.78634337, 6.04389628));
        points.add(new LatLng(50.78642712, 6.04395393));
        points.add(new LatLng(50.78650458, 6.04400529));
        points.add(new LatLng(50.78658832, 6.04406353));
        points.add(new LatLng(50.78667035, 6.04412682));
        points.add(new LatLng(50.78675086, 6.04418285));
        points.add(new LatLng(50.78683293, 6.04425392));
        points.add(new LatLng(50.78691759, 6.04434427));
        points.add(new LatLng(50.7869987, 6.0444214));
        points.add(new LatLng(50.78709728, 6.0445227));
        points.add(new LatLng(50.78716944, 6.04460643));
        points.add(new LatLng(50.78724709, 6.04470408));
        points.add(new LatLng(50.78731749, 6.04481995));
        points.add(new LatLng(50.78738308, 6.04492224));
        points.add(new LatLng(50.78745515, 6.04503181));
        points.add(new LatLng(50.78751302, 6.04517063));
        points.add(new LatLng(50.78757631, 6.04530016));
        points.add(new LatLng(50.78764819, 6.04544033));
        points.add(new LatLng(50.78769653, 6.04557971));
        points.add(new LatLng(50.78774016, 6.04572111));
        points.add(new LatLng(50.7877671, 6.04589908));
        points.add(new LatLng(50.78779041, 6.04605571));
        points.add(new LatLng(50.78781998, 6.04622246));
        points.add(new LatLng(50.78783282, 6.04636695));
        points.add(new LatLng(50.78784104, 6.04652263));
        points.add(new LatLng(50.78785756, 6.04668236));
        points.add(new LatLng(50.78786562, 6.04686001));
        points.add(new LatLng(50.78787264, 6.04702941));
        points.add(new LatLng(50.78788742, 6.04720567));
        points.add(new LatLng(50.78789277, 6.04735055));
        points.add(new LatLng(50.78789755, 6.04751674));
        points.add(new LatLng(50.78789135, 6.04768727));
        points.add(new LatLng(50.78788903, 6.0478431));
        points.add(new LatLng(50.78788308, 6.04800633));
        points.add(new LatLng(50.78789213, 6.04816357));
        points.add(new LatLng(50.78788432, 6.04831662));
        points.add(new LatLng(50.78788021, 6.04848551));
        points.add(new LatLng(50.78786157, 6.04864195));
        points.add(new LatLng(50.78784871, 6.04879583));
        points.add(new LatLng(50.78782719, 6.04895887));
        points.add(new LatLng(50.78781177, 6.04911677));
        points.add(new LatLng(50.7877836, 6.0492809));
        points.add(new LatLng(50.78775634, 6.0494309));
        points.add(new LatLng(50.78772888, 6.04959274));
        points.add(new LatLng(50.78769875, 6.04974502));
        points.add(new LatLng(50.78766636, 6.0498856));
        points.add(new LatLng(50.78763247, 6.05002559));
        points.add(new LatLng(50.78759589, 6.05014959));
        points.add(new LatLng(50.78753773, 6.05022281));
        points.add(new LatLng(50.78747681, 6.05030831));
        points.add(new LatLng(50.78742774, 6.05039022));
        points.add(new LatLng(50.78741196, 6.05050394));
        points.add(new LatLng(50.7874271, 6.05062103));
        points.add(new LatLng(50.78745836, 6.05070175));
        points.add(new LatLng(50.78750393, 6.05072712));
        points.add(new LatLng(50.78759537, 6.05063241));
        points.add(new LatLng(50.78762775, 6.05052314));
        points.add(new LatLng(50.78760919, 6.05038816));
        points.add(new LatLng(50.7875818, 6.05029096));
        points.add(new LatLng(50.78750909, 6.05020982));
        points.add(new LatLng(50.7874446, 6.05019029));
        points.add(new LatLng(50.78741148, 6.05049294));
        points.add(new LatLng(50.78742124, 6.05061311));
        points.add(new LatLng(50.7874645, 6.05070557));
        points.add(new LatLng(50.7874871, 6.05076133));
        points.add(new LatLng(50.78756668, 6.05054282));
        points.add(new LatLng(50.78755773, 6.05041689));
        points.add(new LatLng(50.7874837, 6.05032946));
        points.add(new LatLng(50.78744165, 6.05024516));
        points.add(new LatLng(50.78739249, 6.05025623));
        points.add(new LatLng(50.78731422, 6.05036338));
        points.add(new LatLng(50.78716694, 6.05041933));
        points.add(new LatLng(50.78703136, 6.05044984));
        points.add(new LatLng(50.78688936, 6.05044734));
        points.add(new LatLng(50.78676088, 6.05046381));
        points.add(new LatLng(50.78662562, 6.05047483));
        points.add(new LatLng(50.78648912, 6.05046934));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(50.77738953, 6.0506326)).zoom(14).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition), 1, null);

        googleMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(cameraPosition),
                1,
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onCancel() {
                    }
                }
        );
/*
        currentIndex++;
        highLightMarker(currentIndex);
*/

        int distance = 20;
        final int tilt = 45;
        final int zoomLevel = 16;
        final int turnSpeed = 100;

        final Handler handler = new Handler();
        final double duration = 10000;

        final Interpolator interpolator = new BounceInterpolator();

        for(int c = 0; c < points.size()-distance; c+=distance) {

            final double start = SystemClock.uptimeMillis();
            final LatLng beginPos = points.get(c);
            final LatLng endPos = points.get(c + distance);

            Float heading = (float)computeHeading(beginPos, endPos);

            //animateTo(endPos, 16, heading, tilt, 10000);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    double elapsed = SystemClock.uptimeMillis() - start;
                    float t = Math.max(
                            1 - interpolator.getInterpolation((float) (elapsed / duration)), 0);

                    double lat = (1-t) * endPos.latitude + t * beginPos.latitude;
                    double lng = (1-t) * endPos.longitude + t * beginPos.longitude;

                    Float heading = (float)computeHeading(beginPos, endPos);

                    CameraPosition newCam =
                            new CameraPosition.Builder()
                                    .target(new LatLng(lat,lng))
                                    .bearing(heading)
                                    .tilt(tilt)
                                    .zoom(zoomLevel)
                                    .build();

                    googleMap.animateCamera(
                            CameraUpdateFactory.newCameraPosition(newCam),
                            turnSpeed,
                            null
                    );

                    if (t > 0.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 32);
                    }
                }
            });
        }

        // Perform any camera updates here
        return v;
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