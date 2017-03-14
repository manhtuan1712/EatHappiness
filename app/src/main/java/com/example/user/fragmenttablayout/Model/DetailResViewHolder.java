package com.example.user.fragmenttablayout.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.user.fragmenttablayout.Asyntask.DowloadHinhTask;
import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckImgDetail;
import com.example.user.fragmenttablayout.Object.Anhs;
import com.example.user.fragmenttablayout.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 10/12/2016.
 */

public class DetailResViewHolder extends RecyclerView.ViewHolder {
    ImageView imgquan;
    CheckImgDetail checkImgDetail;
    ArrayList<String> link = new ArrayList<>();


    public DetailResViewHolder(View itemView, CheckImgDetail checkImgDetail) {
        super(itemView);
        this.checkImgDetail = checkImgDetail;
        imgquan = (ImageView) itemView.findViewById(R.id.imagdetail_res);

    }

    public void setData(final Anhs anhs) {
        if (imgquan != null) {
            new DowloadHinhTask(imgquan).execute(anhs.link);
        }
        imgquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkImgDetail.checkImg(anhs.id, link);
            }
        });

    }
}
