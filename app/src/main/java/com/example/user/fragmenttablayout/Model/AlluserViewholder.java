package com.example.user.fragmenttablayout.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.user.fragmenttablayout.R;

/**
 * Created by ManhTuan on 9/21/2016.
 */

public class AlluserViewholder extends RecyclerView.ViewHolder {
    public TextView tvname, tvid, tvmatkhau, tvemail;
    public AlluserViewholder(View itemView) {
        super(itemView);
        tvemail = (TextView)itemView.findViewById(R.id.tvemail);
        tvname = (TextView)itemView.findViewById(R.id.tvname);
        tvid = (TextView)itemView.findViewById(R.id.tvid);
        tvmatkhau = (TextView)itemView.findViewById(R.id.tvpass);
    }
}
