package com.example.user.fragmenttablayout.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fragmenttablayout.Asyntask.DowloadHinhTask;
import com.example.user.fragmenttablayout.Network.ApiType;
import com.example.user.fragmenttablayout.Network.CallApi;
import com.example.user.fragmenttablayout.Network.CallBackData;
import com.example.user.fragmenttablayout.Object.DanhMuc;
import com.example.user.fragmenttablayout.Object.DiaDiem;
import com.example.user.fragmenttablayout.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 9/7/2016.
 */
public class InfoStoreWindowAdapter implements GoogleMap.InfoWindowAdapter, CallBackData {
    private View rootView;
    private Context context;
    private LayoutInflater layoutInflater;
    private TextView tvtenquan, tvdiachi;
    private ImageView imgquan;

    public InfoStoreWindowAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        rootView = layoutInflater.inflate(R.layout.info_window_store, null);

    }

    @Override
    public View getInfoWindow(Marker marker) {
        DiaDiem diaDiem = (DiaDiem) marker.getTag();
        CallApi.getInstance().SetcallBack(this);
        CallApi.getInstance().CallapiServer(ApiType.GET_DIADIEM, null, null);
        tvdiachi = (TextView) rootView.findViewById(R.id.tv_address_info_store);
        tvtenquan = (TextView) rootView.findViewById(R.id.tv_name_info_store);
        imgquan = (ImageView) rootView.findViewById(R.id.img_info_store);

        tvtenquan.setText(diaDiem.ten);
        tvdiachi.setText(diaDiem.diachi);
        if(imgquan != null){
            new DowloadHinhTask(imgquan).execute(diaDiem.anh);
        }


        return rootView;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public void Callback(ApiType apiType, String json) {
//        try {
////            diaDiemList = parseDiaDiem(json);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}
