package com.example.user.fragmenttablayout.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fragmenttablayout.Asyntask.DowloadHinhTask;
import com.example.user.fragmenttablayout.Object.DiaDiem;
import com.example.user.fragmenttablayout.R;

/**
 * Created by ManhTuan on 10/28/2016.
 */

public class CatalogDetailViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgStore;
    public TextView tvMark;
    public TextView tvName;
    public TextView tvAddress;
    public TextView tvDistance, tvlike, tvcmt;
    public CatalogDetailViewHolder(View itemView) {
        super(itemView);
        imgStore = (ImageView) itemView.findViewById(R.id.img_store);
        tvMark = (TextView) itemView.findViewById(R.id.txt_mark);
        tvName = (TextView) itemView.findViewById(R.id.txt_name);
        tvAddress = (TextView) itemView.findViewById(R.id.txt_address);
        tvDistance = (TextView) itemView.findViewById(R.id.txt_distance);
        tvlike = (TextView) itemView.findViewById(R.id.tvlike);
        tvcmt = (TextView) itemView.findViewById(R.id.tvcmt);
    }

    public void setData(final DiaDiem diaDiem) {
        if (imgStore != null) {
            new DowloadHinhTask(imgStore).execute(diaDiem.anh);
        }
        tvMark.setText(diaDiem.diem + "");
        tvName.setText(diaDiem.ten);
        tvAddress.setText(diaDiem.diachi);
        tvlike.setText(String.valueOf(diaDiem.luotxem));
        tvcmt.setText(String.valueOf(diaDiem.luotcomment));
    }
}
