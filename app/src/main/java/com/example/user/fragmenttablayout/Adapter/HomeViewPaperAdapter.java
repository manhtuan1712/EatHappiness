package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.fragmenttablayout.Fragment.Home.Home.FragmentHome;
import com.example.user.fragmenttablayout.Fragment.Home.Home.FragmentMap;
import com.example.user.fragmenttablayout.Fragment.Home.Home.FragmentStore;
import com.example.user.fragmenttablayout.Fragment.Home.YeuThich.Fragment_Bosuutap;
import com.example.user.fragmenttablayout.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 9/6/2016.
 */

public class HomeViewPaperAdapter extends FragmentStatePagerAdapter{
    private static final int NUM_PAGE = 3;
    private Context context;
    private List<Fragment> listData = new LinkedList<Fragment>();

    public HomeViewPaperAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        listData.add(new FragmentHome());
        listData.add(new Fragment_Bosuutap());
        listData.add(new FragmentMap());
    }

    @Override
    public Fragment getItem(int position) {
        return listData.get(position);
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String tilte = "";
        switch (position){
            case 0:
                tilte = context.getString(R.string.an_gi);
                break;
            case 1:
                tilte = "Bộ Sưu Tập";
                break;
            case 2:
                tilte = context.getString(R.string.gan_toi);
                break;
        }
        return tilte;
    }
}
