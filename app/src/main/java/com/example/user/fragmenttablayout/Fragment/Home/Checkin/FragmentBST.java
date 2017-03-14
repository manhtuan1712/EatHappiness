package com.example.user.fragmenttablayout.Fragment.Home.Checkin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.fragmenttablayout.Activity.MainActivity;
import com.example.user.fragmenttablayout.Adapter.BosutapluoiAdapter;
import com.example.user.fragmenttablayout.Adapter.BosuutapNgang;
import com.example.user.fragmenttablayout.Model.BosuutapViewHolder;
import com.example.user.fragmenttablayout.R;
import com.example.user.fragmenttablayout.Utils.ViewUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 9/24/2016.
 */

public class FragmentBST extends Fragment implements CheckImageCallBack, CheckRemoteImageCallBack{
    private View rootView;
    private RecyclerView recyclerViewNgang, recyclerViewDoc;
    private List<Bitmap> bitmaps = new LinkedList<>();
    private BosuutapNgang bosuutapNgang;
    MainActivity mainActivity;
    private ImageView imgback;
    

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }
        rootView = inflater.inflate(R.layout.fragment_galery, null);
        imgback = (ImageView)rootView.findViewById(R.id.imgback);
        recyclerViewNgang = (RecyclerView) rootView.findViewById(R.id.recycler_view_bosuutapchon);
        recyclerViewDoc = (RecyclerView) rootView.findViewById(R.id.recycler_view_bosuutap);
        recyclerViewDoc.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerViewDoc.setAdapter(new BosutapluoiAdapter(getContext(), ViewUtils.getImagesSDCard(), this, this));
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return rootView;
    }

    @Override
    public void Checked(Bitmap bitmap) {
        bosuutapNgang = new BosuutapNgang(getActivity(), bitmaps);
        if (bitmap != null) {
            bosuutapNgang.Add(bitmap);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewNgang.setLayoutManager(linearLayoutManager);
        recyclerViewNgang.setAdapter(bosuutapNgang);

    }

    @Override
    public void CheckedRemove(Bitmap bitmap) {
        if(bitmap!=null){
            bosuutapNgang.removeItem(bitmap);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewNgang.setLayoutManager(linearLayoutManager);
        recyclerViewNgang.setAdapter(bosuutapNgang);
    }

}
