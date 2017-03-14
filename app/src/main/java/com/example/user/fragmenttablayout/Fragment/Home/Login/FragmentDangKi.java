package com.example.user.fragmenttablayout.Fragment.Home.Login;

import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.fragmenttablayout.Fragment.Home.Home.Fragment_New_Home;
import com.example.user.fragmenttablayout.Object.User;
import com.example.user.fragmenttablayout.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 9/17/2016.
 */
public class FragmentDangKi extends Fragment {
    private View rooView;
    private Button btdangki;
    private EditText edtemail, edtpass, edtrepass, edtname;
    private User user;
    private TextView tvdangnhap, tvpass, tvemail, tvrepass, tvname;
    private ImageView imgback;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rooView = inflater.inflate(R.layout.fragment_dangki, null);
        initView();
        setupRegister();
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "CENTURY.ttf");
        tvpass.setTypeface(type);
        tvrepass.setTypeface(type);
        tvname.setTypeface(type);
        tvemail.setTypeface(type);
        user = new User("", edtname.getText().toString(), edtpass.getText().toString(), edtemail.getText().toString());
        return rooView;
    }

    private void initView() {
        btdangki = (Button) rooView.findViewById(R.id.btdangki);
        edtemail = (EditText) rooView.findViewById(R.id.edtemail);
        edtname = (EditText) rooView.findViewById(R.id.edtten);
        edtpass = (EditText) rooView.findViewById(R.id.edtpassword);
        edtrepass = (EditText) rooView.findViewById(R.id.edtrepw);
        tvdangnhap = (TextView) rooView.findViewById(R.id.tvdangnhap);
        imgback = (ImageView) rooView.findViewById(R.id.imgback);
        tvemail = (TextView) rooView.findViewById(R.id.tvemail);
        tvname = (TextView) rooView.findViewById(R.id.tvname);
        tvrepass = (TextView) rooView.findViewById(R.id.tvrepass);
        tvpass = (TextView) rooView.findViewById(R.id.tvpass);

    }

    private void setupRegister() {
        btdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtpass.getText().toString().equals(edtrepass.getText().toString())) {
                    new RegisterTask(getActivity(), edtemail.getText().toString(), edtpass.getText().toString(), edtname.getText().toString()).execute();
                } else {
                    Toast.makeText(getActivity(), "Pass isn't same Repass ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new FragmentDangNhap(), String.valueOf(new FragmentDangNhap().getId())).addToBackStack(null).commit();
            }
        });
    }

    public class RegisterTask extends AsyncTask<String, Void, String> {
        private Context context;
        private String email, pass, name;

        public RegisterTask(Context context, String email, String pass, String name) {
            this.context = context;
            this.email = email;
            this.pass = pass;
            this.name = name;
        }

        @Override
        protected String doInBackground(String... params) {
            String phanhoi = "";
            HttpURLConnection httpURLConnection;
            try {
                URL url = new URL("http://103.237.147.137:9090/api/RegisterUser");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.addRequestProperty("Content-Type", "application/json");
                httpURLConnection.addRequestProperty("Accept", " text/json");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                JSONObject root = new JSONObject();
                root.put("Id", null);
                root.put("TenHienThi", name);
                root.put("MatKhau", pass);
                root.put("Email", email);
                outputStream.write(root.toString().getBytes());
                outputStream.flush();// giải phóng bộ nhớ
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
                    if (phanhoi.equals("1")) {
                        return "Register Successfully";
                    } else {
                        return "" + httpURLConnection.getResponseCode();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Register Failed";
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("Register Successfully")) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new Fragment_New_Home(), String.valueOf(new Fragment_New_Home().getId())).addToBackStack(null).commit();
            } else {
                Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
