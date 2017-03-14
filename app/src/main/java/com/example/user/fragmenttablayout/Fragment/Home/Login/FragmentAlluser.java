package com.example.user.fragmenttablayout.Fragment.Home.Login;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.fragmenttablayout.Asyntask.AlluserTask;
import com.example.user.fragmenttablayout.R;

/**
 * Created by ManhTuan on 9/21/2016.
 */

public class FragmentAlluser extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_alluser, null);
        initView();
        setupAlluser();
        return rootView;
    }

    private void initView(){
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view_alluser);
        button = (Button)rootView.findViewById(R.id.btalluser);
    }

    private void setupAlluser(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlluserTask(getActivity()).execute();
            }
        });
    }
}
