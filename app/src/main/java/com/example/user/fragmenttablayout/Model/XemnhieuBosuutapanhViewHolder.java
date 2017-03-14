package com.example.user.fragmenttablayout.Model;

import android.support.annotation.StringDef;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fragmenttablayout.Asyntask.DowloadHinhTask;
import com.example.user.fragmenttablayout.Object.XemNhieuAnh;
import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/12/2016.
 */
public class XemnhieuBosuutapanhViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView tvTenquan, tvSoluonglike, tvSoluongdiadiem, tvUser;

    public XemnhieuBosuutapanhViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.img_xemnhieu_bosuutapanh);
        tvSoluongdiadiem = (TextView) itemView.findViewById(R.id.soluong_điaiem_bosuutapanh);
        tvSoluonglike = (TextView) itemView.findViewById(R.id.soluong_like_bosuutapanh);
        tvTenquan = (TextView) itemView.findViewById(R.id.tvtenquan_bosuutapanh);
        tvUser = (TextView) itemView.findViewById(R.id.tvuser);
    }

    public void setData(XemNhieuAnh xemNhieuAnh) {
        if(imageView != null){
            new DowloadHinhTask(imageView).execute(xemNhieuAnh.anh);
        }
        tvSoluongdiadiem.setText(String.valueOf(xemNhieuAnh.soluonganh));
        tvTenquan.setText(xemNhieuAnh.ten);
        tvSoluonglike.setText(String.valueOf(xemNhieuAnh.soluongluu));
        tvUser.setText(xemNhieuAnh.user);
    }
}
