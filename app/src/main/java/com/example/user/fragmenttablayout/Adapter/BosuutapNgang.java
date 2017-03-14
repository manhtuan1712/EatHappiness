package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckCheckbox;
import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckImageCallBack;
import com.example.user.fragmenttablayout.Model.BosuutapNgangViewHolder;
import com.example.user.fragmenttablayout.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 9/24/2016.
 */

public class BosuutapNgang extends RecyclerView.Adapter<BosuutapNgangViewHolder> {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<Bitmap> bitmapList = new LinkedList<>();

    public BosuutapNgang(Context context, List<Bitmap> bitmaps){
        layoutInflater = LayoutInflater.from(context);
        this.bitmapList = bitmaps;
        this.context = context;
    }
    public void removeItem(Bitmap bitmap) {
        bitmapList.remove(bitmap);
        notifyDataSetChanged();
    }
    public void Add(Bitmap bitmap){
        bitmapList.add(bitmap);
        notifyDataSetChanged();
    }
    @Override
    public BosuutapNgangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BosuutapNgangViewHolder(layoutInflater.inflate(R.layout.item_ngang, parent, false));
    }

    @Override
    public void onBindViewHolder(final BosuutapNgangViewHolder holder, final int position) {
        holder.setData(bitmapList.get(position));
        holder.checkBox.setChecked(true);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(bitmapList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return bitmapList.size();
    }
}
