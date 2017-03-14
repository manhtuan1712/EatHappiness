package com.example.user.fragmenttablayout.Fragment.Home.Home;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Adapter.HomeAdapter;
import com.example.user.fragmenttablayout.Fragment.Home.Detail.FragmentDetail;
import com.example.user.fragmenttablayout.Object.DanhMuc;
import com.example.user.fragmenttablayout.Object.DiaDiem;
import com.example.user.fragmenttablayout.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 9/1/2016.
 */
public class FragmentNew extends android.support.v4.app.Fragment {
    RecyclerView recyclerView;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_new, null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        new GetDiaDiemTask(getContext()).execute();
        return rootView;
    }


    public List<DiaDiem> parseDiaDiem(String json) throws JSONException {
        List<DiaDiem> diaDiems = new LinkedList<>();
        JSONArray listDiaDiem = new JSONArray(json);
        for (int i = 0; i < listDiaDiem.length(); i++) {
            JSONObject node = listDiaDiem.getJSONObject(i);
            DiaDiem diaDiem = new DiaDiem();
            diaDiem.diem = node.getDouble("diem");
            diaDiem.diachi = node.getString("diachi");
            diaDiem.id = node.getInt("Id");
            diaDiem.anh = node.getString("Anh");
            diaDiem.ten = node.getString("Ten");
            diaDiem.luotxem = node.getInt("luotXem");
            diaDiem.luotcomment = node.getInt("luotComment");
            diaDiem.lat = node.getDouble("lat");
            diaDiem.lng = node.getDouble("lng");
            JSONObject nodeDanhMuc = node.getJSONObject("DanhMuc");
            DanhMuc danhMuc = new DanhMuc();
            danhMuc.id = nodeDanhMuc.getInt("Id");
            danhMuc.ten = nodeDanhMuc.getString("Ten");
            diaDiems.add(diaDiem);
        }

        return diaDiems;
    }

    public class GetDiaDiemTask extends AsyncTask<List<DiaDiem>, Void, List<DiaDiem>> implements CheckRecylerViewRes {
        private Context context;

        public GetDiaDiemTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<DiaDiem> doInBackground(List<DiaDiem>... params) {
            String phanhoi = "";
            HttpURLConnection httpURLConnection;
            try {
                URL url = new URL("http://103.237.147.137:9090/api/DiaDiems");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.addRequestProperty("Content-Type", "application/json");
                httpURLConnection.addRequestProperty("Accept", "text/json");
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK || httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    phanhoi = stringBuffer.toString();
                    List<DiaDiem> diaDiem = new LinkedList<>();
                    diaDiem = parseDiaDiem(phanhoi);
                    return diaDiem;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<DiaDiem> diaDiem) {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            recyclerView.setAdapter(new HomeAdapter(getContext(), diaDiem, this));
        }

        @Override
        public void checkItemRes(int id) {
            FragmentDetail fragmentDetail = new FragmentDetail();
            Bundle bundle = new Bundle();
            bundle.putInt("Id", id);
            fragmentDetail.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, fragmentDetail, String.valueOf(new FragmentDetail().getId())).addToBackStack(null).commit();
        }
    }
}
