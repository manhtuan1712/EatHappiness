package com.example.user.fragmenttablayout.Fragment.Home.Login;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.user.fragmenttablayout.Fragment.Home.Home.Fragment_New_Home;
import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/17/2016.
 */
public class FragmentLogin extends Fragment {
    private View rootView;
    private Button btdangnhap, btdangki, btall;
    private FragmentDangNhap fragmentDangNhap;
    private FragmentDangKi fragmentDangKi;
    private FragmentAlluser fragmentAlluser;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_login, null);
        initView();
        setupFragment();
        return rootView;
    }

    private void initView() {
        btdangnhap = (Button) rootView.findViewById(R.id.btdangnhaplogin);
        btdangki = (Button) rootView.findViewById(R.id.btdangkilogin);
        btall = (Button) rootView.findViewById(R.id.btalluser);
    }

    private void setupFragment() {
        fragmentDangKi = new FragmentDangKi();
        fragmentDangNhap = new FragmentDangNhap();
        fragmentAlluser = new FragmentAlluser();

        btdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new FragmentDangNhap(), "FragmentDang").commit();
            }
        });
        btdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new FragmentDangKi(), String.valueOf(fragmentDangKi.getId())).addToBackStack(null).commit();

            }
        });
        btall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(fragmentAlluser);
            }
        });
    }


    private void replaceFragment(android.app.Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_login, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }
}
