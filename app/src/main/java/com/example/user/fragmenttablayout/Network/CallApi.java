package com.example.user.fragmenttablayout.Network;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by ManhTuan on 9/22/2016.
 */

public class CallApi { // Gọi Request lên Server
    public CallBackData callBackData;
    public static CallApi callApi;
    public static String urlServer = "http://103.237.147.137:9090/";
    public static String urlRegister = urlServer + "api/Users";
    public static String urlLogin = "";
    public static String urlLocation = urlServer + "api/DiaDiems";
    public static String urlAlluser = urlServer + "";
    public static String urlCatalog = urlServer + "api/DanhMucs";
    public static String urlGaleryLocation = urlServer + "api/BoSuuTapDiaDiems";
    public static String urlGaleryPic = urlServer + "api/BoSuuTapAnhs";
    public static String urlDiaDiem = urlServer + "api/DiaDiems";

    public static CallApi getInstance() {
        if (callApi == null) {
            callApi = new CallApi();
        }
        return callApi;
    }

    public void SetcallBack(CallBackData callBackData) { // thực thi xong trả kết quả về cho lớp View, cầu nối giữa View-Net
        this.callBackData = callBackData;
    }

    //Hàm này được View gọi
    public void CallapiServer(ApiType apiType, List<HttpParam> dataHeader, JSONObject dataBody) {
        String urlServer = "";
        String method_all = null;
        Method method = new Method();
        switch (apiType) {
            case GET_DANGNHAP:
                urlServer = urlLogin;
                method_all = method.methodSplit("GET_DANGNHAP");
                break;
            case GET_DIADIEM:
                urlServer = urlLocation;
                method_all = method.methodSplit("GET_DIADIEM");
                break;
            case GET_DANHMUC:
                urlServer = urlCatalog;
                method_all = method.methodSplit("GET_DANHMUC");
                break;
            case GET_BOSUTAPDIADIEM:
                urlServer = urlGaleryLocation;
                method_all = method.methodSplit("GET_BOSUTAPDIADIEM");
                break;
            case GET_BOSUUTAPANH:
                urlServer = urlGaleryPic;
                method_all = method.methodSplit("GET_BOSUUTAPANH");
                break;
            case GET_DIADIEMID:
                urlServer = urlDiaDiem;
                method_all = method.methodSplit("GET_DIADIEMID");
        }
        // tạo data nằm trên url gọi lên server
        if (dataHeader != null) {
            for (HttpParam httpParam : dataHeader) {
                if (dataHeader.indexOf(httpParam) != dataHeader.size() - 1) {
                    urlServer += httpParam.param + "=" + httpParam.value + "&";
                } else {
                    urlServer += httpParam.param + "=" + httpParam.value;
                }
            }
        }
        // tạo data năm trong gói tin HTTP Requests
        new AsyncCallServer(dataBody, method_all, callBackData, apiType).execute(urlServer);
    }

    public class Method {
        ApiType apiType;
        String method;

        public Method() {
        }

        public String methodSplit(String method) {
            String[] split = method.split("_");
            String method_all = split[0];
            return method_all;
        }
    }

    public class AsyncCallServer extends AsyncTask<String, Void, String> {
        public JSONObject body;
        public String method;
        public CallBackData data;
        public ApiType apiType;

        public AsyncCallServer(JSONObject body, String method, CallBackData data, ApiType apiType) {
            this.body = body;
            this.method = method;
            this.data = data;
            this.apiType = apiType;
        }

        @Override
        protected String doInBackground(String... params) {
            String phanhoi = "";
            HttpURLConnection httpURLConnection;
            try {
                URL url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod(method);
                httpURLConnection.addRequestProperty("Content-Type", "application/json");
                httpURLConnection.addRequestProperty("Accept", " text/json");
                httpURLConnection.connect();
                if (body != null) {
                    OutputStream output = httpURLConnection.getOutputStream();
                    output.write(body.toString().getBytes());
                    output.flush();
                }
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader inputreader = new InputStreamReader(input);
                    BufferedReader r = new BufferedReader(inputreader);
                    StringBuilder builder = new StringBuilder();
                    String server;
                    while ((server = r.readLine()) != null) {
                        builder.append(server);
                    }
                    phanhoi = builder.toString();
                    return phanhoi;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            callBackData.Callback(apiType, s);

        }
    }
}
