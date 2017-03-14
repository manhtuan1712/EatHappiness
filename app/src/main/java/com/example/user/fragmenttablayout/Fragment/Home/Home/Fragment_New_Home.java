package com.example.user.fragmenttablayout.Fragment.Home.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Adapter.HomeViewPaperAdapter;
import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/6/2016.
 */

public class Fragment_New_Home extends Fragment {
//    public static Fragment_New_Home fragmentHome;
    private View rootView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private HomeViewPaperAdapter homeViewPaperAdapter;

//    public static Fragment_New_Home getInstance() {
//        if (fragmentHome == null)
//            fragmentHome = new Fragment_New_Home();
//        return fragmentHome;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, null);
        InitView();
        SetData();
        return rootView;
    }

    private void SetData() {
        homeViewPaperAdapter = new HomeViewPaperAdapter(this.getFragmentManager(), getContext());
        viewPager.setAdapter(homeViewPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void InitView() {
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        tabLayout = (TabLayout) rootView.findViewById(R.id.home_tab_layout);
    }

    @Override
    public void onResume() {
        super.onResume();
        InitView();
        SetData();
    }
}
