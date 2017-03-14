package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Model.CatalogDetailViewHolder;
import com.example.user.fragmenttablayout.Object.DiaDiem;
import com.example.user.fragmenttablayout.R;

import java.util.List;

/**
 * Created by ManhTuan on 10/28/2016.
 */

public class CatalogDetailAdapter extends RecyclerView.Adapter<CatalogDetailViewHolder> {
    private List<DiaDiem> diaDiemList;
    private LayoutInflater mLayoutInflater;

    public CatalogDetailAdapter(List<DiaDiem> diaDiems, Context context){
        this.diaDiemList = diaDiems;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public CatalogDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatalogDetailViewHolder(mLayoutInflater.inflate(R.layout.item_catalog_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(CatalogDetailViewHolder holder, int position) {
        holder.setData(diaDiemList.get(position));
    }

    @Override
    public int getItemCount() {
        return diaDiemList.size();
    }
}
