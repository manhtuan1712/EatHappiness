package com.example.user.fragmenttablayout.Model;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.user.fragmenttablayout.Asyntask.DowloadHinhTask;
import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckImageCallBack;
import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckimgDetailCallback;
import com.example.user.fragmenttablayout.R;

import java.util.ArrayList;

/**
 * Created by ManhTuan on 9/29/2016.
 */

public class ViewImgHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    private CheckimgDetailCallback callBack;
    private String link;
    public ViewImgHolder(View itemView, final CheckimgDetailCallback callBack) {
        super(itemView);
        this.callBack = callBack;
        imageView = (ImageView)itemView.findViewById(R.id.img_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.callback(link);
            }
        });
    }

    public void setData(String link){
        this.link = link;
        if (imageView != null) {
            new DowloadHinhTask(imageView).execute(link);
        }
    }
}
