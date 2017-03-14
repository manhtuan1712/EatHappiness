package com.example.user.fragmenttablayout.Asyntask;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.fragmenttablayout.Adapter.AlluserAdapter;
import com.example.user.fragmenttablayout.Object.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 9/21/2016.
 */

public class AlluserTask extends AsyncTask<String, Object, String> {
    private Context context;
    private RecyclerView recyclerView;
    private AlluserAdapter adapter;

    public AlluserTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String phanhoi="";
        HttpURLConnection httpURLConnection;
        try{
            URL url = new URL("http://103.237.147.137:9090/User/GetAllUsers");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.addRequestProperty("Content-Type", "application/json");
            httpURLConnection.addRequestProperty("Accept", " text/json");
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();
                if(inputStream!=null){
                    inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder builder = new StringBuilder();
                    String chunk;
                    while((chunk = bufferedReader.readLine()) != null){
                        builder.append(chunk);
                    }
                    phanhoi = builder.toString();
                    List<User> result = new LinkedList<>();
                    JSONArray userlist = new JSONArray(phanhoi);
                    for (int i = 0; i < userlist.length(); i++) {
                        JSONObject user = userlist.getJSONObject(i);
                        if (user != null) {
                            User userObject = new User();
                            if (!user.isNull("Id")) {
                                userObject.id = user.getString("Id");
                            }
                            if(!user.isNull("TenHienThi")){
                                userObject.ten = user.getString("TenHienThi");
                            }
                            if(!user.isNull("MatKhau")){
                                userObject.matkhau = user.getString("MatKhau");
                            }
                            if(!user.isNull("Email")){
                                userObject.email = user.getString("Email");
                            }
                            result.add(userObject);

                        }

                    }
                }
            }
        }
        catch (Exception e){

        }
        return phanhoi;
    }

    @Override
    protected void onPostExecute(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(s);
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
