package com.gamingdronzz.yts.Fragments;

/**
 * Created by balpreet on 3/26/2018.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import com.gamingdronzz.yts.R;
import com.gamingdronzz.yts.Tabs.ViewPagerAdapter;

import java.util.HashMap;
public class FragmentHome extends Fragment implements TabLayout.OnTabSelectedListener {

    private TextView welcomeText, ccaDeskText;
    View view;
    final String TAG = "Home";
    private TabLayout tabLayout;
    //This is our viewPager
    private ViewPager viewPager;
    ViewPagerAdapter adapter;
    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setUpTabLayout(view);
        return view;
    }
    private void setUpTabLayout(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);

        tabLayout = setUpTablayout(tabLayout);
        //Initializing viewPager
        viewPager = view.findViewById(R.id.viewPagerLayout);

        //Creating our pager blogAdapter
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        //Adding blogAdapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(this);
        viewPager.setOffscreenPageLimit(5);
        viewPager.setCurrentItem(0);
        viewPager.getAdapter().notifyDataSetChanged();

    }



    private TabLayout setUpTablayout(TabLayout tabLayout) {
        // Create Tabs Here
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //tabLayout.setSmoothScrollingEnabled(true);


        //tabLayout.getTabAt(0).setIcon(R.drawable.group);

        //Add Title here
        tabLayout.getTabAt(0).setText("LATEST UPLOADS");
        tabLayout.getTabAt(1).setText("POPULAR DOWNLOADS");
        //tabLayout.getTabAt(2).setText("RECENTLY\nVISITED");


        return tabLayout;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Log.d(TAG, "Selected position = " + tab.getPosition());
        int position = tab.getPosition();
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}