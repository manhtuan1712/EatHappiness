package com.example.user.fragmenttablayout.Fragment.Home.YeuThich;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/11/2016.
 */
public class Fragment_Cuaban extends Fragment {
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cuaban, null)
;        return rootView;
    }
}
