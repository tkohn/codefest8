package de.codefest8.gamification8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;

/**
 * Created by koerfer on 07.03.2015.
 */
public class TrackDetailFragment extends SupportMapFragment {

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private List<Marker> markers;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init();
        return inflater.inflate(R.layout.fragment_trackdetail, container, false);
    }

    public void addMarkerToMap(LatLng latLng) {
        Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng)
                .title("Trip")
                .snippet("snippet"));
        markers.add(marker);
    }

    public void init()
    {
        mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        googleMap = mapFragment.getMap();
        //googleMap.setMyLocationEnabled(true);
    }
}
