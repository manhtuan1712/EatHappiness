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

import com.example.user.fragmenttablayout.Activity.MainActivity;
import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/11/2016.
 */
public class Fragment_Bosuutapanh extends Fragment {
    private View rootView;
    private Fragment_Cuaban_Bosuutapanh fragment_cuaban_bosuutapnhieu;
    private Fragment_Xemnhieu_Bosuutapanh fragment_xemnhieu_bosuutapanh;
    private TabLayout tabLayout;
    public MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_bosuutapanh, null);
        initView();
        listenerChangeTab();
        setUpTab();
        return rootView;
    }

    private void initView() {
        tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tab_bosuutapanh);
    }

    private void setUpTab() {
        fragment_cuaban_bosuutapnhieu = new Fragment_Cuaban_Bosuutapanh();
        fragment_xemnhieu_bosuutapanh = new Fragment_Xemnhieu_Bosuutapanh();
        tabLayout.addTab(tabLayout.newTab().setText("Của Bạn"));
        tabLayout.addTab(tabLayout.newTab().setText("Xem Nhiều"));
    }

    private void listenerChangeTab() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurentTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setCurentTab(int position) {
        switch (position) {
            case 0:
                replaceFragment(fragment_cuaban_bosuutapnhieu);
                break;
            case 1:
                replaceFragment(fragment_xemnhieu_bosuutapanh);
                break;
        }
    }

    private void replaceFragment(android.app.Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_bosuutapanh, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

}
