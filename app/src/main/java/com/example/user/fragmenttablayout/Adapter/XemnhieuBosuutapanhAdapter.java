package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Model.XemnhieuBosuutapanhViewHolder;
import com.example.user.fragmenttablayout.Object.XemNhieuAnh;
import com.example.user.fragmenttablayout.R;

import java.util.List;

/**
 * Created by User on 9/12/2016.
 */
public class XemnhieuBosuutapanhAdapter extends RecyclerView.Adapter<XemnhieuBosuutapanhViewHolder> {
    private LayoutInflater layoutInflater;
    private int[] mImagesID;
    private String[] mTenquan;
    private String[] mSoluongdiadiem;
    private String[] mSoluongthich;
    List<XemNhieuAnh> xemNhieuAnhs;

    public XemnhieuBosuutapanhAdapter(Context context, List<XemNhieuAnh> xemNhieuAnhs) {
        layoutInflater = LayoutInflater.from(context);
        Resources resources = context.getResources();
        this.xemNhieuAnhs = xemNhieuAnhs;
        mTenquan = resources.getStringArray(R.array.xemnhieu_bosuutapanh_tenquan);
        mSoluongdiadiem = resources.getStringArray(R.array.xemnhieu_bosuutapanh_sodiadiem);
        mSoluongthich = resources.getStringArray(R.array.xemnhieu_bosuutapanh_soluonglike);
        TypedArray typedArray = resources.obtainTypedArray(R.array.xemnhieu_bosuutapanh_anhquan);
        int imagecount = typedArray.length();
        mImagesID = new int[imagecount];
        for(int i=0; i<imagecount; i++){
            mImagesID[i] = typedArray.getResourceId(i,1);
        }
        typedArray.recycle();
    }

    @Override
    public XemnhieuBosuutapanhViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new XemnhieuBosuutapanhViewHolder(layoutInflater.inflate(R.layout.item_xemnhieu_bosuutapanh, parent, false));
    }

    @Override
    public void onBindViewHolder(XemnhieuBosuutapanhViewHolder holder, int position) {
        holder.setData(xemNhieuAnhs.get(position));
    }

    @Override
    public int getItemCount() {
        return xemNhieuAnhs.size();
    }
}
