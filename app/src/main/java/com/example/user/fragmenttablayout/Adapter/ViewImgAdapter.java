package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CompletionInfo;

import com.example.user.fragmenttablayout.Asyntask.DowloadHinhTask;
import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckImageCallBack;
import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckimgDetailCallback;
import com.example.user.fragmenttablayout.Model.ViewImgHolder;
import com.example.user.fragmenttablayout.R;
import com.example.user.fragmenttablayout.Utils.ViewUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 9/29/2016.
 */

public class ViewImgAdapter extends RecyclerView.Adapter<ViewImgHolder> {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<String> duongdanList = new ArrayList<>();
    private CheckimgDetailCallback callBack;

    public ViewImgAdapter(Context context, ArrayList<String> duongdanList,CheckimgDetailCallback callBack){
        this.callBack = callBack;
        this.context = context;
        this.duongdanList = duongdanList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public ViewImgHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewImgHolder(inflater.inflate(R.layout.item_viewimg, parent, false), callBack);
    }

    @Override
    public void onBindViewHolder(ViewImgHolder holder, int position) {
        holder.setData(duongdanList.get(position));
    }

    @Override
    public int getItemCount() {
        return duongdanList.size();
    }
}
