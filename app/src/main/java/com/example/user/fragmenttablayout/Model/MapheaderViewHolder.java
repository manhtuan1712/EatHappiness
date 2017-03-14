package com.example.user.fragmenttablayout.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckDanhMuc;
import com.example.user.fragmenttablayout.Object.DanhMuc;
import com.example.user.fragmenttablayout.R;

/**
 * Created by User on 9/14/2016.
 */
public class MapheaderViewHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    private ImageView imageView;
    CheckDanhMuc checkDanhMuc;

    public MapheaderViewHolder(View itemView, final CheckDanhMuc checkDanhMuc) {
        super(itemView);
        this.checkDanhMuc = checkDanhMuc;
        textView = (TextView)itemView.findViewById(R.id.tvmap);


    }

    public void setData(final DanhMuc danhMuc){
        textView.setText(danhMuc.ten);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDanhMuc.CheckedCheckBox(danhMuc.id);

            }
        });
    }
}
