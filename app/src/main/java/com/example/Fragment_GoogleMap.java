package com.example.huangyoude.sa;

//Tab主要功能
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//GoogleMap
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CameraPosition;

public class Fragment_GoogleMap extends Fragment{
    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mapFragmentView = inflater.inflate(R.layout.fragment_googlemap, container, false);
        mMapView = (MapView) mapFragmentView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch(Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                //For showing a move to my location button
                //googleMap.setMyLocationEnabled(true);

                //For dropping a marker at a point on the Map
                if (((Activity_Main)getActivity()).getLocation() == null) {
                    LatLng fjuLocation = new LatLng(25.0365, 121.4324);
                    googleMap.addMarker(new MarkerOptions().position(fjuLocation).title("Marker Title").snippet("Marker Description"));

                    //For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(fjuLocation).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }

                else{
                    LatLng setLocation = ((Activity_Main)getActivity()).getLocation();
                    setMarker(setLocation);
                    setCameraPosition(setLocation);
                    ((Activity_Main)getActivity()).setNewLocation(null);
                }


            }
        });

        return mapFragmentView;

    }


    //Write a function that can set Latitude and Longitude
    public LatLng setLocation(int Lat , int Long){
        LatLng setedLocation = new LatLng(Lat , Long);

        return setedLocation;
    }

    public void setMarker(LatLng setMarkerLocation){
        googleMap.addMarker(new MarkerOptions().position(setMarkerLocation).title("Title").snippet("Description"));
    }

    public void setCameraPosition(LatLng setCameraLocation){
        CameraPosition setCameraPosition = new CameraPosition.Builder().target(setCameraLocation).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(setCameraPosition));
    }

    @Override
    public void onResume(){
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}


