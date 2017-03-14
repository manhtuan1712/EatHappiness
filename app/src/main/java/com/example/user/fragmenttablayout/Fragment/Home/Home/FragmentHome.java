package com.example.user.fragmenttablayout.Fragment.Home.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.R;

;

/**
 * Created by User on 9/1/2016.
 */
public class FragmentHome extends android.support.v4.app.Fragment {
    private TabLayout allTabLayout;
    private FragmentNew fragmentNew;
    private FragmentCatalog fragmentCatalog;
    private FragmentZone fragmentZone;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_eat_what, null);
        InitView();
        ListenerChangeTab();
        SetupTabLayout();
        return rootView;
    }


    private void SetupTabLayout() {
        fragmentNew = new FragmentNew();
        fragmentCatalog = new FragmentCatalog();
        fragmentZone = new FragmentZone();
        allTabLayout.addTab(allTabLayout.newTab().setText(getString(R.string.home_new_title)), true);
        allTabLayout.addTab(allTabLayout.newTab().setText(getString(R.string.home_catalog_title)));
        allTabLayout.addTab(allTabLayout.newTab().setText(getString(R.string.home_zone_title)));
    }

    private void InitView() {
        allTabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tab);
    }

    private void ListenerChangeTab() {
        allTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setCurrentTabFragment(int position) {
        switch (position) {
            case 0:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new FragmentNew(), String.valueOf(new FragmentNew().getId())).commit();
                break;
            case 1:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new FragmentCatalog(), String.valueOf(new FragmentCatalog().getId())).commit();
                break;
            case 2:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new FragmentZone(), String.valueOf(new FragmentZone().getId())).commit();
                break;
        }
    }



}
