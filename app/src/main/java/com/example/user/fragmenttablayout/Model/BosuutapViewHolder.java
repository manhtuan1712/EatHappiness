package com.example.user.fragmenttablayout.Model;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckImageCallBack;
import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckRemoteImageCallBack;
import com.example.user.fragmenttablayout.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 9/24/2016.
 */

public class BosuutapViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public CheckBox checkBox;
    public CheckImageCallBack checkImageCallBack;
    public CheckRemoteImageCallBack checkRemoteImageCallBack;
    public Bitmap bitmap;
    public List<Bitmap> bitmapList = new LinkedList<>();

    public BosuutapViewHolder(View itemView, final CheckImageCallBack callBack, final CheckRemoteImageCallBack callBackRemote) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imgbstluoi);
        checkBox = (CheckBox)itemView.findViewById(R.id.checkBox);
        this.checkImageCallBack = callBack;
        this.checkRemoteImageCallBack = callBackRemote;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    callBack.Checked(bitmap);
                }
                else  if(isChecked==false){
                    callBackRemote.CheckedRemove(bitmap);
                    checkBox.setChecked(false);
                }
            }
        });


    }

    public void setData(Bitmap bitmap) {
        this.bitmap = bitmap;
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }

    }
}
