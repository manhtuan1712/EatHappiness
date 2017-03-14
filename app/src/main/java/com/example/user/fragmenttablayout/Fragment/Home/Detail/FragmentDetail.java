package com.example.user.fragmenttablayout.Fragment.Home.Detail;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.fragmenttablayout.Adapter.DetailResAdapter;
import com.example.user.fragmenttablayout.Asyntask.DowloadHinhTask;
import com.example.user.fragmenttablayout.Fragment.Home.Checkin.FragmentBST;
import com.example.user.fragmenttablayout.Fragment.Home.Checkin.FragmentCamera;
import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckImgDetail;
import com.example.user.fragmenttablayout.Object.Anhs;
import com.example.user.fragmenttablayout.Object.DanhMuc;
import com.example.user.fragmenttablayout.Object.DiaDiem;
import com.example.user.fragmenttablayout.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 9/30/2016.
 */

public class FragmentDetail extends Fragment {
    private View rootView;
    private ImageView imgcheckin, imgtaianh, imganhvavideo, imgchitiet;
    private TextView tvtenquan, tvslogan, tvgiomocua, tvdiachi, tvdanhmuc, tvgiatien, tvbinhluan, tvhinhanh, tvcheckin, tvluulai, tvdiem, tvtenquantittle;
    private RecyclerView recyclerView;
    private ImageButton imageButton;
    ArrayList<String> listlink;
    int type;
    String link;
    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = this.getArguments();
        int myInt = bundle.getInt("Id", 0);
        new GetDiaDiemTaskID(getContext(), myInt).execute();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_chitiet_quanan, null);
        initView();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        imgtaianh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new FragmentCamera(), String.valueOf(new FragmentCamera().getId())).addToBackStack(null).commit();
            }
        });
        imgcheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new FragmentBST(), String.valueOf(new FragmentBST().getId())).addToBackStack(null).commit();
            }
        });
        return rootView;

    }

    private void initView() {
        imgcheckin = (ImageView) rootView.findViewById(R.id.imgcheckin);
        imgtaianh = (ImageView) rootView.findViewById(R.id.imgtaianh);
        imganhvavideo = (ImageView) rootView.findViewById(R.id.imganhvavideo);
        tvdanhmuc = (TextView) rootView.findViewById(R.id.tvdanhmuc);
        tvdiachi = (TextView) rootView.findViewById(R.id.tvdiachi);
        tvtenquan = (TextView) rootView.findViewById(R.id.tvtenquan);
        tvslogan = (TextView) rootView.findViewById(R.id.tvslogan);
        tvgiomocua = (TextView) rootView.findViewById(R.id.tvgiomocua);
        tvgiatien = (TextView) rootView.findViewById(R.id.tvgiatien);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recylerview_imglistchitiet);
        imgchitiet = (ImageView) rootView.findViewById(R.id.imgchitiet);
        imageButton = (ImageButton) rootView.findViewById(R.id.imgback);
        tvbinhluan = (TextView) rootView.findViewById(R.id.tvbinhluan);
        tvcheckin = (TextView) rootView.findViewById(R.id.tvcheckin);
        tvluulai = (TextView) rootView.findViewById(R.id.tvluulai);
        tvhinhanh = (TextView) rootView.findViewById(R.id.tvhinhanh);
        tvdiem = (TextView) rootView.findViewById(R.id.tvdiem);
        tvtenquantittle = (TextView) rootView.findViewById(R.id.tvtenquantittle);
    }

    private void replaceFragment(android.app.Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container_chitiet, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public class GetDiaDiemTaskID extends AsyncTask<DiaDiem, Void, DiaDiem> implements CheckImgDetail {
        private Context context;
        private int id;

        public GetDiaDiemTaskID(Context context, int ID) {
            this.context = context;
            this.id = ID;
        }

        @Override
        protected DiaDiem doInBackground(DiaDiem... params) {
            String phanhoi = "";
            HttpURLConnection httpURLConnection;
            try {
                URL url = new URL("http://103.237.147.137:9090/api/DiaDiems/" + id);
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
                    JSONObject node = new JSONObject(phanhoi);
                    DiaDiem diaDiem = new DiaDiem();
                    diaDiem.diem = node.getDouble("diem");
                    diaDiem.diachi = node.getString("diachi");
                    diaDiem.id = node.getInt("Id");
                    diaDiem.anh = node.getString("Anh");
                    diaDiem.ten = node.getString("Ten");
                    diaDiem.luotcomment = node.getInt("luotComment");
                    diaDiem.luotanh = node.getInt("LuotAnh");
                    diaDiem.luotcheckin = node.getInt("LuotCheckIn");
                    diaDiem.giomocua = node.getString("GioMoCua");
                    diaDiem.giatien = node.getString("GiaTien");
                    diaDiem.slogan = node.getString("GioiThieu");
                    JSONObject nodeDanhmuc = node.getJSONObject("DanhMuc");
                    DanhMuc danhMuc = new DanhMuc();
                    danhMuc.ten = nodeDanhmuc.getString("Ten");
                    diaDiem.danhMuc = danhMuc;
                    JSONArray nodeAnh = node.getJSONArray("Anhs");
                    List<Anhs> anhsList = new LinkedList<>();
                    listlink = new ArrayList<String>();
                    for(int i=0; i<nodeAnh.length();i++) {
                        JSONObject nodeAnhs = nodeAnh.getJSONObject(i);
                        Anhs anhs = new Anhs();
                        anhs.id = nodeAnhs.getInt("Id");
                        anhs.type = nodeAnhs.getInt("type");
                        anhs.link = nodeAnhs.getString("link");
                        anhs.diadiemid = nodeAnhs.getInt("DiaDiemId");
                        listlink.add(anhs.link);
                        anhsList.add(anhs);
                    }
                    diaDiem.anhses = anhsList;

                    return diaDiem;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final DiaDiem diaDiem) {
            tvtenquantittle.setText(diaDiem.ten);
            tvtenquan.setText(diaDiem.ten);
            tvdiachi.setText(diaDiem.diachi);
            tvdiem.setText(String.valueOf(diaDiem.diem));
            tvbinhluan.setText(String.valueOf(diaDiem.luotcomment));
            tvhinhanh.setText(String.valueOf(diaDiem.luotanh));
            tvcheckin.setText(String.valueOf(diaDiem.luotcheckin));
            tvgiomocua.setText(diaDiem.giomocua);
            tvgiatien.setText(diaDiem.giatien);
            tvslogan.setText(diaDiem.slogan);
            tvdanhmuc.setText(diaDiem.danhMuc.ten);
            if (imgchitiet != null) {
                new DowloadHinhTask(imgchitiet).execute(diaDiem.anh);
            }
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
            recyclerView.setAdapter(new DetailResAdapter(getContext(), diaDiem.anhses, this));
            for(int i = 0; i< diaDiem.anhses.size(); i++){
                link = diaDiem.anhses.get(i).link;
                if(diaDiem.anhses.get(i).type == 0){
                    imganhvavideo.setVisibility(View.GONE);
                }
                else if (diaDiem.anhses.get(i).type == 1){

                    if (imganhvavideo != null) {
                       imganhvavideo.setVisibility(View.VISIBLE);
                        imganhvavideo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                FragmentViewVideo fragmentViewVideo = new FragmentViewVideo();
                                Bundle bundle = new Bundle();
                                bundle.putInt("Type", 1);
                                bundle.putString("Link",link);
                                fragmentViewVideo.setArguments(bundle);
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, fragmentViewVideo, String.valueOf(fragmentViewVideo.getId())).addToBackStack(null).commit();
                            }
                        });
                    }
                }
            }
        }

        @Override
        public void checkImg(int id, ArrayList<String> link) {
            type = 0;
            link = new ArrayList<String>(listlink);
            FragmentViewVideo fragmentViewVideo = new FragmentViewVideo();
            Bundle bundle = new Bundle();
            bundle.putInt("Id", id);
            bundle.putInt("Type", type);
            bundle.putStringArrayList("Link", link);
            fragmentViewVideo.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, fragmentViewVideo, String.valueOf(fragmentViewVideo.getId())).addToBackStack(null).commit();
        }
    }


}
