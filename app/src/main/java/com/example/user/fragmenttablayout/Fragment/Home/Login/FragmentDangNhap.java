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
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.fragmenttablayout.Fragment.Home.Home.Fragment_New_Home;
import com.example.user.fragmenttablayout.Object.User;
import com.example.user.fragmenttablayout.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static com.example.user.fragmenttablayout.R.id.email;

/**
 * Created by User on 9/17/2016.
 */
public class FragmentDangNhap extends Fragment {
    private View rootView;
    private Button btdangnhap;
    private EditText edtemail, edtpassword;
    private TextView tvdangnhap, tvdangki, tvemail, tvpass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dangnhap, null);
        initView();
        setupLogin();
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "CENTURY.ttf");
        tvpass.setTypeface(type);
        tvdangnhap.setTypeface(type);
        tvdangki.setTypeface(type);
        tvemail.setTypeface(type);
        return rootView;
    }

    private void initView() {
        btdangnhap = (Button) rootView.findViewById(R.id.btdangnhap);
        edtemail = (EditText) rootView.findViewById(R.id.edtemail);
        edtpassword = (EditText) rootView.findViewById(R.id.edtpassword);
        tvdangki = (TextView) rootView.findViewById(R.id.tvdangki);
        tvdangnhap = (TextView) rootView.findViewById(R.id.tvdangnhap);
        tvemail = (TextView) rootView.findViewById(R.id.tvemail);
        tvpass = (TextView) rootView.findViewById(R.id.tvpass);
    }

    private void setupLogin() {
        btdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginTask(edtemail.getText().toString(), edtpassword.getText().toString(), getActivity()).execute();
            }
        });
        tvdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new FragmentDangKi(), String.valueOf(new FragmentDangKi().getId())).addToBackStack(null).commit();
            }
        });
    }

    public class LoginTask extends AsyncTask<String, Void, String> {

        private User user = new User();
        private Context context;
        private String emails, pass;

        public LoginTask(String emails, String pass, Context context) {
            this.emails = emails;
            this.pass = pass;
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            String respond = null;
            HttpURLConnection httpURLConnection;
            try {
                URL url = new URL("http://103.237.147.137:9090/api/LoginUser?email=" + emails + "&password=" + pass);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");// set phương thức gọi server
                httpURLConnection.addRequestProperty("Content-Type", "application/json");//set phương thức Json hoặc XML
                httpURLConnection.addRequestProperty("Accept", "text/json");//Tên, giá trị, set kiểu truyền dữ liệu từ server
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK || httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    if (inputStream != null) {
                        inputStream = new BufferedInputStream(inputStream);
                        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        StringBuilder data = new StringBuilder();
                        String chunks;
                        while ((chunks = bufferedReader.readLine()) != null) {
                            data.append(chunks);
                        }
                        respond = data.toString();
                        if (respond.equals("1")) {
                            return respond;
                        } else {
                            return ""+ httpURLConnection.getResponseCode();
                        }
                    }
                }


            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String check) {
            if (check.equals("1")) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new Fragment_New_Home(), String.valueOf(new Fragment_New_Home().getId())).commit();
            } else {
                Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
