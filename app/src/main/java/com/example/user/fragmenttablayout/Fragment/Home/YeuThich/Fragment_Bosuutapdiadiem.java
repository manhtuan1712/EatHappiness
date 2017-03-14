package com.example.user.fragmenttablayout.Fragment.Home.YeuThich;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.user.fragmenttablayout.Network.ApiType;
import com.example.user.fragmenttablayout.Network.CallBackData;
import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/10/2016.
 */
public class Fragment_Bosuutapdiadiem extends Fragment {
    private View rootView;
    private TabLayout tabLayout;
    private Fragment_NoiBat fragment_noiBat;
    private Fragment_XemNhieu fragment_xemNhieu;
    private Fragment_Cuaban fragment_cuaban;
    private Fragment_Daluu fragment_daluu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_bosuutapdiadiem, null);
        InitView();
        listenerTabChange();
        setUpTab();
        return rootView;
    }

    private void setUpTab() {
        fragment_cuaban = new Fragment_Cuaban();
        fragment_daluu = new Fragment_Daluu();
        fragment_xemNhieu = new Fragment_XemNhieu();
        tabLayout.addTab(tabLayout.newTab().setText("Của Bạn"));
        tabLayout.addTab(tabLayout.newTab().setText("Xem Nhiều"), true);
        tabLayout.addTab(tabLayout.newTab().setText("Đã Lưu"));
    }

    private void InitView() {
        tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tab_yeuthich);
    }

    private void listenerTabChange() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                replaceFragment(fragment_cuaban);
                break;
            case 1:
                replaceFragment(fragment_xemNhieu);
                break;
            case 2:
                replaceFragment(fragment_daluu);
                break;
        }
    }

    private void replaceFragment(android.app.Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_yeuthich, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

}
