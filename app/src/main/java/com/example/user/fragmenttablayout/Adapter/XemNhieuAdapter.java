package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Model.XemNhieuViewHolder;
import com.example.user.fragmenttablayout.Object.XemNhieu;
import com.example.user.fragmenttablayout.R;

import java.util.List;

/**
 * Created by User on 9/10/2016.
 */
public class XemNhieuAdapter extends RecyclerView.Adapter<XemNhieuViewHolder> {
    private int[] mImagesID;
    private String[] mTenquan;
    private String[] mSoluongDiadiem;
    private String[] mSoluongLike;
    private LayoutInflater layoutInflater;
    List<XemNhieu> xemNhieus;

    public XemNhieuAdapter(Context context, List<XemNhieu> xemNhieus) {
        this.xemNhieus = xemNhieus;
        layoutInflater = LayoutInflater.from(context);
        Resources resources = context.getResources();
        mTenquan = resources.getStringArray(R.array.xemnhieu_tenquan);
        mSoluongDiadiem = resources.getStringArray(R.array.xemnhieu_sodiadiem);
        mSoluongLike = resources.getStringArray(R.array.xemnhieu_soluonglike);
        TypedArray typedArray = resources.obtainTypedArray(R.array.xemnhieu_anhquan);
        int imgcount = typedArray.length();
        mImagesID = new int[imgcount];
        for (int i = 0; i < imgcount; i++) {
            mImagesID[i] = typedArray.getResourceId(i, 1);
        }
        typedArray.recycle();
    }

    @Override
    public XemNhieuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new XemNhieuViewHolder(layoutInflater.inflate(R.layout.item_xemnhieu, parent, false));
    }

    @Override
    public void onBindViewHolder(XemNhieuViewHolder holder, int position) {
        holder.setData(xemNhieus.get(position));
    }

    @Override
    public int getItemCount() {
        return xemNhieus.size();
    }
}
