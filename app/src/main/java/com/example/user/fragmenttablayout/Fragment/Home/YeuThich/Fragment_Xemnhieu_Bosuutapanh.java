package com.example.user.fragmenttablayout.Fragment.Home.YeuThich;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Adapter.XemnhieuBosuutapanhAdapter;
import com.example.user.fragmenttablayout.Network.ApiType;
import com.example.user.fragmenttablayout.Network.CallApi;
import com.example.user.fragmenttablayout.Network.CallBackData;
import com.example.user.fragmenttablayout.Object.XemNhieuAnh;
import com.example.user.fragmenttablayout.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 9/11/2016.
 */
public class Fragment_Xemnhieu_Bosuutapanh extends Fragment implements CallBackData {
    private View rootView;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_xemnhieu_bosutapanh, null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_xemnhieu_bosuutapanh);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        CallApi.getInstance().SetcallBack(this);
        CallApi.getInstance().CallapiServer(ApiType.GET_BOSUUTAPANH, null, null);
        return rootView;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void Callback(ApiType apiType, String json) {
        switch (apiType) {
            case GET_BOSUUTAPANH:
                try {
                    List<XemNhieuAnh> result = parseXemnhieuAnh(json);
                    recyclerView.setAdapter(new XemnhieuBosuutapanhAdapter(getContext(), result));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }
    }

    private List<XemNhieuAnh> parseXemnhieuAnh(String json) throws JSONException {
        List<XemNhieuAnh> xemNhieuAnhs = new LinkedList<>();
        JSONArray root = new JSONArray(json);
        for (int i = 0; i < root.length(); i++) {
            JSONObject node = root.getJSONObject(i);
            XemNhieuAnh xemNhieuAnh = new XemNhieuAnh();
            xemNhieuAnh.anh = node.getString("Anh");
            xemNhieuAnh.id = node.getInt("Id");
            xemNhieuAnh.soluonganh = node.getInt("Soluonganh");
            xemNhieuAnh.soluongluu = node.getInt("Soluongluu");
            xemNhieuAnh.user = node.getString("User");
            xemNhieuAnh.ten = node.getString("Ten");
            xemNhieuAnhs.add(xemNhieuAnh);
        }
        return xemNhieuAnhs;
    }
}
