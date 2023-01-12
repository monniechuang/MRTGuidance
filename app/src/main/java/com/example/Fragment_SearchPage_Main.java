package com.example.huangyoude.sa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Button;
import android.view.View.OnClickListener;


public class Fragment_SearchPage_Main extends Fragment implements ViewPager.OnPageChangeListener {

    private Button touchSearchButton;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private BottomNavigationView navigation;
    private ViewPager viewPager;

    private Fragment_SearchPage_SelectedRestaurant fragment1 = new Fragment_SearchPage_SelectedRestaurant();
    private Fragment_SearchPage_NearbyRestaurant fragment2 = new Fragment_SearchPage_NearbyRestaurant();
    private Fragment_SearchPage_RestaurantType fragment3 = new Fragment_SearchPage_RestaurantType();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View homeSearchPageView = inflater.inflate(R.layout.fragment_searchpage_main, container, false);
        //setContentView(R.layout.activity_search_page_home);

        touchSearchButton = (Button)homeSearchPageView.findViewById(R.id.touchSearch);
        touchSearchButton.setOnClickListener(touchSearch);

        viewPager = (ViewPager) homeSearchPageView.findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(this);
        navigation = (BottomNavigationView) homeSearchPageView.findViewById(R.id.search_page_home_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return fragment1;
                    case 1:
                        return fragment2;
                    case 2:
                        return fragment3;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        // ATTENTION: This was auto-generated to handle app links.
/*
        Intent appLinkIntent = getActivity().getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        */

        return homeSearchPageView;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            viewPager.setCurrentItem(item.getOrder());
            return true;
        }

    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        navigation.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

private OnClickListener touchSearch = new OnClickListener() {
    @Override
    public void onClick(View v) {

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.searchlayout , new Fragment_SearchPage_Search())
                .addToBackStack(null)
                .commit();

    }
};
        /*
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.searchlayout , new Fragment_SearchPage_Search())
                .addToBackStack(null)
                .commit();
        */
        /*
        Intent intent = new Intent();
        intent.setClass(SearchPageHome.this  , SearchPage.class);
        startActivity(intent);
        */
}

