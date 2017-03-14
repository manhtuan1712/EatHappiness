package com.example.user.fragmenttablayout.Fragment.Home.YeuThich;


import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.fragmenttablayout.Adapter.HomeAdapter;
import com.example.user.fragmenttablayout.Adapter.XemNhieuAdapter;
import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckRecylerViewRes;
import com.example.user.fragmenttablayout.Network.ApiType;
import com.example.user.fragmenttablayout.Network.CallApi;
import com.example.user.fragmenttablayout.Network.CallBackData;
import com.example.user.fragmenttablayout.Object.DiaDiem;
import com.example.user.fragmenttablayout.Object.XemNhieu;
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
 * Created by User on 9/10/2016.
 */
public class Fragment_XemNhieu extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_xemnhieu, null);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_xemnhieu);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        new GetXemNhieuTask(getActivity()).execute();
        return rootView;
    }

    public class GetXemNhieuTask extends AsyncTask<List<XemNhieu>, Void, List<XemNhieu>> {
        private Context context;

        public GetXemNhieuTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<XemNhieu> doInBackground(List<XemNhieu>... params) {
            String phanhoi = "";
            HttpURLConnection httpURLConnection;
            try {
                URL url = new URL("http://103.237.147.137:9090/api/BoSuuTapDiaDiems");
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
                    List<XemNhieu> diaDiem = new LinkedList<>();
                    diaDiem = parsingXemnhieu(phanhoi);
                    return diaDiem;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<XemNhieu> diaDiem) {
            recyclerView.setAdapter(new XemNhieuAdapter(getContext(), diaDiem));
        }


        private List<XemNhieu> parsingXemnhieu(String json) throws JSONException {
            List<XemNhieu> xemNhieus = new LinkedList<>();
            JSONArray listXemNhieu = new JSONArray(json);
            for (int i = 0; i < listXemNhieu.length(); i++) {
                JSONObject node = listXemNhieu.getJSONObject(i);
                XemNhieu xemNhieu = new XemNhieu();
                xemNhieu.anh = node.getString("Anh");
                xemNhieu.ten = node.getString("Ten");
                xemNhieu.id = node.getInt("Id");
                xemNhieu.soluongdiem = node.getInt("Soluongdiem");
                xemNhieu.soluongluu = node.getInt("Soluongluu");
                xemNhieu.user = node.getString("User");
                xemNhieus.add(xemNhieu);

            }
            return xemNhieus;
        }
    }
}
