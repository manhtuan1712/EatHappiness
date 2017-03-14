package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.user.fragmenttablayout.Model.CheckinHolder;
import com.example.user.fragmenttablayout.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 9/24/2016.
 */

public class CheckInApdater extends RecyclerView.Adapter<CheckinHolder> {
    List<Bitmap> data = new LinkedList<>();
    private View rootView;
    private LayoutInflater layoutInflater;
    private CheckinHolder viewHolder;
    private Bitmap bitmap;


    public void Add(Bitmap bitmap) {
        data.add(bitmap);
        notifyDataSetChanged();
    }

    public CheckInApdater(Context context, List<Bitmap> bitmap) {
        this.data = bitmap;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CheckinHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        rootView = layoutInflater.inflate(R.layout.item_camera, parent, false);
        viewHolder = new CheckinHolder(rootView);
        return viewHolder;
    }

    private void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(CheckinHolder holder, final int position) {
        holder.imageView.setImageBitmap(data.get(position));
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.checkBox.setChecked(true);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                removeItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
