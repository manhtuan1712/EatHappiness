package com.example.user.fragmenttablayout.Fragment.Home.Home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import com.example.user.fragmenttablayout.Adapter.ZoneAdapter;
import com.example.user.fragmenttablayout.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 9/5/2016.
 */

public class FragmentZone_TPHCM extends Fragment {
    ExpandableListView expandableListView;
    View rootView;
    List<String> quan;
    List<String> duong_q1;
    List<String> duong_q2;
    List<String> duong_qtb;
    List<String> duong_qtp;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_zone_tphcm, container, false);
        spinner = (Spinner) rootView.findViewById(R.id.spinnerzone);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.explistView);
        quan = new ArrayList<String>();
        duong_q1 = new ArrayList<String>();
        duong_q2 = new ArrayList<String>();
        duong_qtb = new ArrayList<String>();
        duong_qtp = new ArrayList<String>();
        HashMap<String, List<String>> duong = new HashMap<String, List<String>>();
        String quan_item[] = getResources().getStringArray(R.array.quan);
        String duongq1[] = getResources().getStringArray(R.array.duong_q1);
        String duongq2[] = getResources().getStringArray(R.array.duong_q2);
        String duongqtb[] = getResources().getStringArray(R.array.duong_qtb);
        String duongqtp[] = getResources().getStringArray(R.array.duong_qtp);
        for (String title : quan_item) {
            quan.add(title);
        }
        for (String title : duongq1) {
            duong_q1.add(title);
        }
        for (String title : duongq2) {
            duong_q2.add(title);
        }
        for (String title : duongqtb) {
            duong_qtb.add(title);
        }
        for (String title : duongqtp) {
            duong_qtp.add(title);
        }

        duong.put(quan.get(0), duong_q1);
        duong.put(quan.get(1), duong_q2);
        duong.put(quan.get(2), duong_qtb);
        duong.put(quan.get(3), duong_qtp);
        ZoneAdapter zoneAdapter = new ZoneAdapter(getActivity(), quan, duong);
        expandableListView.setAdapter(zoneAdapter);
        return rootView;
    }



}
