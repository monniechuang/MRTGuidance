package com.example.huangyoude.sa;

//Tab功能import
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

//本體
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;


public class Activity_Main extends AppCompatActivity implements ViewPager.OnPageChangeListener ,
    TabLayout.OnTabSelectedListener{

    private LatLng newLocation;

    public LatLng getLocation(){
        return newLocation;
    }

    public void setNewLocation (LatLng location){
        this.newLocation = location;
    }

    //Tab功能設計
    private ViewPager viewPager;
    private TabLayout tabLayout;
    //Tab設定頁面
    private Layout_Fortest mainPage = new Layout_Fortest();
    private Layout_Search Search = new Layout_Search();
    private Layout_Diary mealDiary = new Layout_Diary();
    private Layout_Personalfile Personalfile = new Layout_Personalfile();
    @Override
    protected void onCreate(Bundle outState) {
        //阻止Activity儲存fragment的狀態
        super.onCreate(outState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.mainviewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //Tab註冊Listener
        viewPager.addOnPageChangeListener(this);
        tabLayout.addOnTabSelectedListener(this);

        //將Fragment匯入到viewPager
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return mainPage;
                    case 1:
                        return Search;
                    case 2:
                        return mealDiary;
                    case 3:
                        return Personalfile;
            }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //TabLayout中TabItem被點擊時觸發
            viewPager.setCurrentItem(tab.getPosition());
        }

    @Override
    public void onTabUnselected(TabLayout.Tab tab){

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab){

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

    }

    @Override
    public void onPageSelected(int position) {
    //viewPager滑動後觸發
        tabLayout.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state){

    }

    /*public class TransmitLocation {
        private LatLng newLocation;

        public LatLng getLocation(){
            return newLocation;
        }

        public void setNewLocation (LatLng location){
            this.newLocation = location;
        }
    }
    */

}
    /*  //還沒使用的Code
    protected void onclick() {
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    */

