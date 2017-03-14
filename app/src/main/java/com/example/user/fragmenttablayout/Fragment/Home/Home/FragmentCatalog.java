package com.example.user.fragmenttablayout.Fragment.Home.Home;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.fragmenttablayout.Adapter.CatalogAdapter;
import com.example.user.fragmenttablayout.Object.DanhMuc;
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
public class FragmentCatalog extends android.support.v4.app.Fragment {
    private View rootView;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_catalog, null);
        InnitView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        new GetDanhMucTask(getActivity()).execute();
        return rootView;
    }

    private void InnitView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listview_catalog);
    }
    public class GetDanhMucTask extends AsyncTask<List<DanhMuc>, Void, List<DanhMuc>> implements CheckRecylerViewRes, CheckCataLogName {
        private Context context;

        public GetDanhMucTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<DanhMuc> doInBackground(List<DanhMuc>... params) {
            String phanhoi = "";
            HttpURLConnection httpURLConnection;
            try {
                URL url = new URL("http://103.237.147.137:9090/api/DanhMucs");
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
                    List<DanhMuc> danhmuc = parseDanhMucs(phanhoi);
                    return danhmuc;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public List<DanhMuc> parseDanhMucs(String json) throws JSONException {
            List<DanhMuc> danhMucs = new LinkedList<>();
            JSONArray listDanhmuc = new JSONArray(json);
            for (int i = 0; i < listDanhmuc.length(); i++) {
                JSONObject node = listDanhmuc.getJSONObject(i);
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.ten = node.getString("Ten");
                danhMuc.id = node.getInt("Id");
                danhMucs.add(danhMuc);
            }
            return danhMucs;
        }

        @Override
        protected void onPostExecute(List<DanhMuc> danhMucs) {
            recyclerView.setAdapter(new CatalogAdapter(getContext(), danhMucs, this, this));
        }


        @Override
        public void checkItemRes(int id) {
            FragmentCatalogDetail fragmentDetail = new FragmentCatalogDetail();
            Bundle bundle = new Bundle();
            bundle.putInt("Id", id);
            fragmentDetail.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragmentDetail, String.valueOf(new FragmentCatalogDetail().getId())).addToBackStack(null).commit();
        }

        @Override
        public void checkCatalogName(String name) {
            Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
        }
    }
}
