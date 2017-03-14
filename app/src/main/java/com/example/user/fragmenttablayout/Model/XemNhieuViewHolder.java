package com.example.user.fragmenttablayout.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fragmenttablayout.Asyntask.DowloadHinhTask;
import com.example.user.fragmenttablayout.Object.XemNhieu;
import com.example.user.fragmenttablayout.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 9/10/2016.
 */
public class XemNhieuViewHolder extends RecyclerView.ViewHolder {
    private ImageView img_xemnhieu;
    private TextView tvTenquan;
    private TextView tvSoluongDiadiem;
    private TextView tvSoluongLike, tvUser;

    public XemNhieuViewHolder(View itemView) {
        super(itemView);
        img_xemnhieu = (ImageView) itemView.findViewById(R.id.img_xemnhieu);
        tvTenquan = (TextView) itemView.findViewById(R.id.tvtenquan);
        tvSoluongDiadiem = (TextView) itemView.findViewById(R.id.soluong_điaiem);
        tvSoluongLike = (TextView) itemView.findViewById(R.id.soluong_like);
        tvUser = (TextView) itemView.findViewById(R.id.tvuser);
    }

    public void setData(XemNhieu xemNhieu) {
        if(img_xemnhieu != null){
            new DowloadHinhTask(img_xemnhieu).execute(xemNhieu.anh);
        }
        tvTenquan.setText(xemNhieu.ten);
        tvSoluongDiadiem.setText(String.valueOf(xemNhieu.soluongdiem));
        tvSoluongLike.setText(String.valueOf(xemNhieu.soluongluu));
        tvUser.setText(xemNhieu.user);
    }
}
