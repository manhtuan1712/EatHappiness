package com.example.user.fragmenttablayout.Model;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckCheckbox;
import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckImageCallBack;
import com.example.user.fragmenttablayout.R;

/**
 * Created by ManhTuan on 9/24/2016.
 */

public class BosuutapNgangViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public CheckBox checkBox;


    public BosuutapNgangViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imgbstngang);
        checkBox = (CheckBox) itemView.findViewById(R.id.cbngang);
    }

    public void setData(Bitmap bitmap) {
        if(bitmap!=null) {
            imageView.setImageBitmap(bitmap);
        }

    }
}
