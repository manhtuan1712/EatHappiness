package com.example.user.fragmenttablayout.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/11/2016.
 */
public class NoiBatViewHolder extends RecyclerView.ViewHolder {
    private TextView tvtenquan, tvsoluongdiadiem, tvsoluongluu;
    private ImageView imgnoibat;

    public NoiBatViewHolder(View itemView) {
        super(itemView);
        tvtenquan = (TextView) itemView.findViewById(R.id.tvtenquan_noibat);
        tvsoluongdiadiem = (TextView) itemView.findViewById(R.id.soluong_điaiem_noibat);
        tvsoluongluu = (TextView) itemView.findViewById(R.id.soluong_like_noibat);
        imgnoibat = (ImageView) itemView.findViewById(R.id.img_noibat);
    }

    public void setData(int img, String tenquan, String soluongdiadiem, String soluongluu) {
        imgnoibat.setImageResource(img);
        tvtenquan.setText(tenquan);
        tvsoluongdiadiem.setText(soluongdiadiem);
        tvsoluongluu.setText(soluongluu);


    }
}
