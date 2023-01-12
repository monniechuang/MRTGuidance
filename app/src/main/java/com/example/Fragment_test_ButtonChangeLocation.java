package com.example.huangyoude.sa;

//Tab主要功能
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;



/**
 * Created by tomoya on 4/5/17.
 */

public class Fragment_test_ButtonChangeLocation extends Fragment {

    Fragment_GoogleMap googleMapSetting = new Fragment_GoogleMap();

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private ImageView changeLocationButton;

    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View buttonChangeLocation = inflater.inflate(R.layout.printscreen_fordemonstration_mrtline, container, false);

        changeLocationButton = (ImageView) buttonChangeLocation.findViewById(R.id.MRTLine);

        changeLocationButton.setOnClickListener(LocationChangeButton);




        return buttonChangeLocation;
    }


    //Define button function
    private OnClickListener LocationChangeButton = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == changeLocationButton.getId()){
                //LatLng i = googleMapSetting.setLocation(24 , 24);
                //googleMapSetting.setMarker(i);
                //googleMapSetting.setCameraPosition(i);
                LatLng i = new LatLng (25.0365, 121.4324);
                ((Activity_Main)getActivity()).setNewLocation(i);
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fortest, new Fragment_GoogleMap())
                        .addToBackStack(null)
                        .commit();
            }

        }
    };
}