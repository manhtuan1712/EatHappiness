package com.example.user.fragmenttablayout.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckCataLogName;
import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckRecylerViewRes;
import com.example.user.fragmenttablayout.Object.DanhMuc;
import com.example.user.fragmenttablayout.R;

/**
 * Created by ManhTuan on 10/6/2016.
 */

public class CatalogViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;
    CheckRecylerViewRes checkRecylerViewRes;
    CheckCataLogName checkCataLogName;
    public CatalogViewHolder(View itemView, CheckRecylerViewRes checkRecylerViewRes, CheckCataLogName checkCataLogName) {
        super(itemView);
        this.checkRecylerViewRes = checkRecylerViewRes;
        this.checkCataLogName = checkCataLogName;
        imageView = (ImageView) itemView.findViewById(R.id.img_catalog);
        textView = (TextView) itemView.findViewById(R.id.tv_name);
    }

    public void setData(final DanhMuc danhMuc, int img){
        imageView.setImageResource(img);
        textView.setText(danhMuc.ten);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRecylerViewRes.checkItemRes(danhMuc.id);
                checkCataLogName.checkCatalogName(danhMuc.ten);
            }
        });
    }
}
