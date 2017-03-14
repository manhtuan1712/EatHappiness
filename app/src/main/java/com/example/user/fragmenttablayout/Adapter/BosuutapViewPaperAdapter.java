package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.fragmenttablayout.Fragment.Home.YeuThich.Fragment_Bosuutapanh;
import com.example.user.fragmenttablayout.Fragment.Home.YeuThich.Fragment_Bosuutapdiadiem;
import com.example.user.fragmenttablayout.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 9/11/2016.
 */
public class BosuutapViewPaperAdapter extends FragmentStatePagerAdapter {
    private int NUM_PAGE = 2;
    private Context context;
    private List<Fragment> list = new LinkedList<Fragment>();

    public BosuutapViewPaperAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        list.add(new Fragment_Bosuutapdiadiem());
        list.add(new Fragment_Bosuutapanh());
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return NUM_PAGE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = context.getString(R.string.bosuutapdiadiem);
                break;
            case 1:
                title = context.getString(R.string.bosuutapanh);
                break;
        }
        return title;
    }
}
