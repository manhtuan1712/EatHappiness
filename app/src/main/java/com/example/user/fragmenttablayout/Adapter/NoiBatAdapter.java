package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Model.NoiBatViewHolder;
import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/11/2016.
 */
public class NoiBatAdapter extends RecyclerView.Adapter<NoiBatViewHolder> {
    private LayoutInflater layoutInflater;
    private int[] mImagesID;
    private String[] mTenquan;
    private String[] mSodiadiem;
    private String[] mSoluongluu;

    public NoiBatAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NoiBatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoiBatViewHolder(layoutInflater.inflate(R.layout.item_noibat, parent, false));
    }

    @Override
    public void onBindViewHolder(NoiBatViewHolder holder, int position) {
        holder.setData(mImagesID[position], mTenquan[position], mSodiadiem[position], mSoluongluu[position]);
    }

    @Override
    public int getItemCount() {
        return mImagesID.length;
    }
}
