package com.example.user.fragmenttablayout.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.user.fragmenttablayout.Adapter.CheckInApdater;
import com.example.user.fragmenttablayout.R;

/**
 * Created by ManhTuan on 9/24/2016.
 */

public class CheckinHolder extends RecyclerView.ViewHolder {
    public CheckBox checkBox;
    public ImageView imageView;


    public CheckinHolder(View itemView) {
        super(itemView);
        checkBox = (CheckBox) itemView.findViewById(R.id.cbcamera);
        imageView = (ImageView) itemView.findViewById(R.id.imgcamera);
    }
}
