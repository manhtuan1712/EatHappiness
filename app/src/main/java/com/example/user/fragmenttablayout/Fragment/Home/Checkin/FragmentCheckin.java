package com.example.user.fragmenttablayout.Fragment.Home.Checkin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.fragmenttablayout.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 9/24/2016.
 */

//public class FragmentCheckin extends Fragment {
//    private View rootView;
//    private ImageView imgcamera, imgalbum;
//    private List<Bitmap> img = new LinkedList<>();
//    private FragmentCamera fragmentCamera;
//    private FragmentBST fragmentBST;
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        rootView = inflater.inflate(R.layout.fragment_checkin, null);
//        initView();
//        setupFragment();
//        return rootView;
//    }
//
//    private void initView() {
//        imgalbum = (ImageView) rootView.findViewById(R.id.imgalbum);
//        imgcamera = (ImageView) rootView.findViewById(R.id.imgcamera);
//    }
//
//    private void setupFragment() {
//        fragmentBST = new FragmentBST();
//        fragmentCamera = new FragmentCamera();
//        imgcamera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                replaceFragment(fragmentCamera);
//            }
//        });
//        imgalbum.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                replaceFragment(fragmentBST);
//            }
//        });
//    }
//
//    private void replaceFragment(android.app.Fragment fragment) {
//        FragmentManager fragmentManager = getActivity().getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.containerCheckin, fragment);
//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        fragmentTransaction.commit();
//    }
//
//}
