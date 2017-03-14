package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckCataLogName;
import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckRecylerViewRes;
import com.example.user.fragmenttablayout.Model.CatalogViewHolder;
import com.example.user.fragmenttablayout.Object.DanhMuc;
import com.example.user.fragmenttablayout.R;

import java.util.List;

/**
 * Created by User on 9/3/2016.
 */

public class CatalogAdapter extends RecyclerView.Adapter<CatalogViewHolder> {
    private int[] mImagesID;
    private String[] mName;
    private LayoutInflater inflater;
    private Context context;
    List<DanhMuc> danhMucs;
    CheckRecylerViewRes checkRecylerViewRes;
    CheckCataLogName checkCataLogName;

    public CatalogAdapter(Context context, List<DanhMuc> danhMucs, CheckRecylerViewRes checkRecylerViewRes, CheckCataLogName checkCataLogName){
        inflater = LayoutInflater.from(context);
        this.checkCataLogName = checkCataLogName;
        this.checkRecylerViewRes = checkRecylerViewRes;
        this.danhMucs = danhMucs;
        this.context = context;
        final Resources resources = context.getResources();
        this.mName = resources.getStringArray(R.array.data_catalog_item);
        final TypedArray typedArray = context.getResources().obtainTypedArray(R.array.data_catalog_image);
        final int imageCount = this.mName.length;
        mImagesID = new int[imageCount];
        for(int i=0; i<imageCount; i++){
            mImagesID[i] = typedArray.getResourceId(i,0);
        }
        typedArray.recycle();


    }
    @Override
    public CatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatalogViewHolder(inflater.inflate(R.layout.item_catalog, parent, false), checkRecylerViewRes, checkCataLogName);
    }

    @Override
    public void onBindViewHolder(CatalogViewHolder holder, int position) {
        holder.setData(danhMucs.get(position), mImagesID[position]);
    }

    @Override
    public int getItemCount() {
        return danhMucs.size();
    }
}
