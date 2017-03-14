package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckDanhMuc;
import com.example.user.fragmenttablayout.Model.MapheaderViewHolder;
import com.example.user.fragmenttablayout.Object.DanhMuc;
import com.example.user.fragmenttablayout.R;

import java.util.List;

/**
 * Created by User on 9/14/2016.
 */
public class MenuHeaderMapAdapter extends RecyclerView.Adapter<MapheaderViewHolder> {
    private LayoutInflater layoutInflater;
    List<DanhMuc> danhMucs;
    CheckDanhMuc checkDanhMuc;
    public MenuHeaderMapAdapter(Context context, List<DanhMuc> danhMuc, CheckDanhMuc checkDanhMuc) {
        layoutInflater = LayoutInflater.from(context);
        this.danhMucs = danhMuc;
        this.checkDanhMuc = checkDanhMuc;

    }

    @Override
    public MapheaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MapheaderViewHolder(layoutInflater.inflate(R.layout.item_menu_header_map, parent, false), checkDanhMuc);
    }

    @Override
    public void onBindViewHolder(MapheaderViewHolder holder, int position) {
        holder.setData(danhMucs.get(position));
    }

    @Override
    public int getItemCount() {
        return danhMucs.size();
    }
}
