package com.example.user.fragmenttablayout.Asyntask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 9/15/2016.
 */
public class DowloadHinhTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView imageView;
    public DowloadHinhTask(ImageView imageView){
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(params[0]);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
               InputStream inputStream = httpURLConnection.getInputStream();
                if(inputStream!=null){
                    return BitmapFactory.decodeStream(inputStream);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
        }
    }


}
