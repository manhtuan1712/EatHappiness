package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckImgDetail;
import com.example.user.fragmenttablayout.Model.DetailResViewHolder;
import com.example.user.fragmenttablayout.Object.Anhs;
import com.example.user.fragmenttablayout.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 10/12/2016.
 */

public class DetailResAdapter extends RecyclerView.Adapter<DetailResViewHolder> {
    LayoutInflater inflater;
    List<Anhs> anhses;
    CheckImgDetail checkImgDetail;
    public DetailResAdapter(Context context, List<Anhs> anhses, CheckImgDetail checkImgDetail) {
        this.anhses = anhses;
        this.checkImgDetail = checkImgDetail;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public DetailResViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailResViewHolder(inflater.inflate(R.layout.itemdetail_res, parent, false),checkImgDetail);
    }

    @Override
    public void onBindViewHolder(DetailResViewHolder holder, int position) {
        holder.setData(anhses.get(position));
    }

    @Override
    public int getItemCount() {
        if(anhses.size()>6) {
            return 6;
        }
        else return anhses.size();
    }
}
