package com.example.user.fragmenttablayout.Fragment.Home.YeuThich;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Adapter.BosuutapViewPaperAdapter;
import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/11/2016.
 */
public class Fragment_Bosuutap extends Fragment {
    private View rootView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BosuutapViewPaperAdapter bosuutapViewPaperAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_bosuutap, null);
        initView();
        setData();
        return rootView;
    }

    private void initView() {
        tabLayout = (TabLayout) rootView.findViewById(R.id.bosuutap_tab_layout);
        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager_bosuutap);
    }

    private void setData() {
        bosuutapViewPaperAdapter = new BosuutapViewPaperAdapter(this.getFragmentManager(), getContext());
        viewPager.setAdapter(bosuutapViewPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
