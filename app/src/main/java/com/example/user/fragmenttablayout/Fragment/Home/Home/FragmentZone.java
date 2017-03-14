package com.example.user.fragmenttablayout.Fragment.Home.Home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/1/2016.
 */
public class FragmentZone extends android.support.v4.app.Fragment {
    private Spinner spinner;
    private FragmentZone_TPHCM fragmentZone_tphcm;
    private Fragment_Zone_Hanoi fragment_zone_hanoi;
    private Fragment_Zone_Chon fragment_zone_chon;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_zone, container, false);
        InitView();
        SetupTabLayout();
        ListenerChangeSpinner();
        return rootView;
    }

    private void SetupTabLayout(){
        fragment_zone_hanoi = new Fragment_Zone_Hanoi();
        fragmentZone_tphcm = new FragmentZone_TPHCM();
        fragment_zone_chon = new Fragment_Zone_Chon();
    }

    private void InitView(){
        spinner = (Spinner)rootView.findViewById(R.id.spinnerzone);
    }

    private void ListenerChangeSpinner(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spinner.getSelectedItem().toString().equalsIgnoreCase("TP.HCM")){
                    replaceFragment(fragmentZone_tphcm);
                }
                else if(spinner.getSelectedItem().toString().equalsIgnoreCase("Hà Nội")){
                    replaceFragment(fragment_zone_hanoi);
                }
                else if(spinner.getSelectedItem().toString().equalsIgnoreCase("Chọn Tỉnh Thành...")){
                    replaceFragment(fragment_zone_chon);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_zone,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
