package com.example.user.fragmenttablayout.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ManhTuan on 9/27/2016.
 */

public class ViewUtils {

    public static List<String> getImagesSDCard() {
        List<String> result = new LinkedList<>();
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        //lấy folder gốc chứa ảnh
        File sdCardFile = new File(sdcard + "/DCIM/Camera/");
        //lấy danh sách ảnh từ folder
        File[] listImage = sdCardFile.listFiles();
        if (listImage != null) {
            //lấy danh sách đường dẫn ảnh
            for (File file : listImage) {
                result.add(file.getAbsolutePath());
            }
        }
            return result;

    }

    public static Bitmap resizeBitmapByScale(Bitmap bitmap, int width, int height, boolean recycle) {

        return bitmap.createScaledBitmap(bitmap, width, height, false);
    }

    private static Bitmap.Config getConfig(Bitmap bitmap) {
        Bitmap.Config config = bitmap.getConfig();
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return config;
    }

}
