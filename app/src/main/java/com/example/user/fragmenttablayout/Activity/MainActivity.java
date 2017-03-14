package com.example.user.fragmenttablayout.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.fragmenttablayout.Fragment.Home.Login.FragmentDangNhap;
import com.example.user.fragmenttablayout.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new FragmentDangNhap(), String.valueOf(new FragmentDangNhap().getId())).commit();
        }
    }
}
