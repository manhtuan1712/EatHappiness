package com.example.user.fragmenttablayout.Fragment.Home.Checkin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.fragmenttablayout.Activity.MainActivity;
import com.example.user.fragmenttablayout.Adapter.CheckInApdater;
import com.example.user.fragmenttablayout.R;

import java.util.LinkedList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ManhTuan on 9/26/2016.
 */

public class FragmentCamera extends android.support.v4.app.Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private ImageButton imageButton, imgback, imgbst;
    private List<Bitmap> bitmaps = new LinkedList<>();
    MainActivity mainActivity;

    public void setMainActivity(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_camera, null);
        initView();
        //Code xin quyền
        if (getContext().checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 100);
        }
        setupCamera();
        return rootView;
    }

    private void initView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_camera);
        imageButton = (ImageButton) rootView.findViewById(R.id.imgbuttocamera);
        imgback = (ImageButton) rootView.findViewById(R.id.imgback);
        imgbst = (ImageButton) rootView.findViewById(R.id.imgbuttonbst);
    }

    private void setupCamera() {
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                capturePic();
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            private void capturePic() {
                if (getContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i, 100);
                } else {
                    Toast.makeText(getContext(), "No Camera", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            CheckInApdater checkInApdater = new CheckInApdater(getActivity(), bitmaps);
            checkInApdater.Add(bitmap);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            recyclerView.setAdapter(checkInApdater);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        }
    }
}
