package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Model.AlluserViewholder;
import com.example.user.fragmenttablayout.Object.User;
import com.example.user.fragmenttablayout.R;

import java.util.List;

/**
 * Created by ManhTuan on 9/21/2016.
 */

public class AlluserAdapter extends RecyclerView.Adapter<AlluserViewholder> {
    private LayoutInflater layoutInflater;
    private List<User> userList;
    private Context context;

    public AlluserAdapter(List<User> userList, Context context) {
        layoutInflater = LayoutInflater.from(context);
        this.userList = userList;
        this.context = context;
    }

    @Override
    public AlluserViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlluserViewholder(layoutInflater.inflate(R.layout.item_alluser, parent, false));
    }

    @Override
    public void onBindViewHolder(AlluserViewholder holder, int position) {
        User user = userList.get(position);
        holder.tvname.setText(user.getTen());
        holder.tvid.setText(user.getId());
        holder.tvmatkhau.setText(user.getMatkhau());
        holder.tvemail.setText(user.getEmail());

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
