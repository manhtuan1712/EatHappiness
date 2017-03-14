package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckCheckbox;
import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckImageCallBack;
import com.example.user.fragmenttablayout.Fragment.Home.Checkin.CheckRemoteImageCallBack;
import com.example.user.fragmenttablayout.Model.BosuutapViewHolder;
import com.example.user.fragmenttablayout.R;
import com.example.user.fragmenttablayout.Utils.ViewUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 9/24/2016.
 */

public class BosutapluoiAdapter extends RecyclerView.Adapter<BosuutapViewHolder>{
    private List<String> duongDanAnhList = new LinkedList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Bitmap> bitmaps = new LinkedList<>();
    private CheckImageCallBack checkImageCallBack;
    private CheckRemoteImageCallBack checkRemoteImageCallBack;
    private BosuutapViewHolder holder;

    public BosutapluoiAdapter(Context context, List<String> duongDanAnhList, CheckImageCallBack callBack, CheckRemoteImageCallBack callBackRemote) {
        this.duongDanAnhList = duongDanAnhList;
        this.context = context;
        this.checkImageCallBack = callBack;
        this.checkRemoteImageCallBack = callBackRemote;
        layoutInflater = LayoutInflater.from(context);
        for(String duongdan : duongDanAnhList){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inDither = true;
            options.inSampleSize = 1;
            Bitmap bitmap = BitmapFactory.decodeFile(duongdan);
            if(bitmap!=null) {
                bitmap = ViewUtils.resizeBitmapByScale(bitmap, 500, 400 , false);
                bitmaps.add(bitmap);
            }
        }
    }

    @Override
    public BosuutapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BosuutapViewHolder(layoutInflater.inflate(R.layout.item_hinhdoc, parent, false), checkImageCallBack, checkRemoteImageCallBack);
    }

    @Override
    public void onBindViewHolder(BosuutapViewHolder holder, int position) {
        holder.setData(bitmaps.get(position));
    }

    @Override
    public int getItemCount() {
        return bitmaps.size();
    }

}
