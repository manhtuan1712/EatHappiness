package com.example.user.fragmenttablayout.Fragment.Home.Home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.user.fragmenttablayout.Adapter.NewHeaderApdapter;
import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/5/2016.
 */

public class Fragment_New_Header extends Fragment {
    private View rootView;
    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_new_header,container,false);
        InitView();
        SetData();
        return rootView;
    }

    private void InitView(){
        gridView = (GridView)rootView.findViewById(R.id.gvnew);
    }

    private void SetData() {
        gridView.setAdapter(new NewHeaderApdapter(getActivity(), null));
    }
}
