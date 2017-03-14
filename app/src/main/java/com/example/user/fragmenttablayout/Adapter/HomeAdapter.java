package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckRecylerViewRes;
import com.example.user.fragmenttablayout.Model.RestaurantViewHolder;
import com.example.user.fragmenttablayout.Object.DiaDiem;
import com.example.user.fragmenttablayout.R;

import java.util.List;

/**
 * Created by User on 9/3/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    private List<DiaDiem> diaDiemList;
    private LayoutInflater mLayoutInflater;
    CheckRecylerViewRes checkRecylerViewRes;


    public HomeAdapter (Context context, List<DiaDiem> diaDiems, CheckRecylerViewRes checkRecylerViewRes){
        this.diaDiemList = diaDiems;
        mLayoutInflater = LayoutInflater.from(context);
        this.checkRecylerViewRes = checkRecylerViewRes;
    }
    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RestaurantViewHolder(mLayoutInflater.inflate(R.layout.item_new, parent, false), checkRecylerViewRes);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.setData(diaDiemList.get(position));

    }

    @Override
    public int getItemCount() {
        return diaDiemList.size();
    }
}
