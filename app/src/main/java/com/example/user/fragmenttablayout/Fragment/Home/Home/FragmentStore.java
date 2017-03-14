package com.example.user.fragmenttablayout.Fragment.Home.Home;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/6/2016.
 */

public class FragmentStore extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
