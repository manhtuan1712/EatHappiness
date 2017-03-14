package com.example.user.fragmenttablayout.Fragment.Home.YeuThich;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.fragmenttablayout.Adapter.NoiBatAdapter;
import com.example.user.fragmenttablayout.Adapter.XemNhieuAdapter;
import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/10/2016.
 */
public class Fragment_NoiBat extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_noibat, null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_noibat);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(new NoiBatAdapter(getActivity()));
        return rootView;
    }
}
