package com.example.user.fragmenttablayout.Fragment.Home.Detail;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.user.fragmenttablayout.Adapter.ViewImgAdapter;
import com.example.user.fragmenttablayout.Asyntask.DowloadHinhTask;
import com.example.user.fragmenttablayout.Fragment.Home.Home.CheckimgDetailCallback;
import com.example.user.fragmenttablayout.R;

import java.util.ArrayList;

/**
 * Created by ManhTuan on 9/29/2016.
 */

public class FragmentViewVideo extends Fragment implements CheckimgDetailCallback {
    private View rootView;
    private VideoView videoView;
    private RecyclerView recyclerView;
    private ImageView imageView;
    private int type;
    FrameLayout frameLayout;
    LinearLayout linearLayout;
    String link;

    //0 là video, 1 là img
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_view_quanan, null);
        initView();
        Bundle bundle = this.getArguments();
        int myInt = bundle.getInt("Id", 0);
        link = bundle.getString("Link");
        ArrayList<String> link = bundle.getStringArrayList("Link");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new ViewImgAdapter(getContext(), link, this));
        type = bundle.getInt("Type");
        if (type == 0) {
            frameLayout.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        } else if (type == 1) {
            frameLayout.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            setupVideoView();
        }
        return rootView;
    }

    private void initView() {
        linearLayout = (LinearLayout) rootView.findViewById(R.id.linearimg);
        frameLayout = (FrameLayout) rootView.findViewById(R.id.frame_video);
        videoView = (VideoView) rootView.findViewById(R.id.videoView);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_viewimg);
        imageView = (ImageView) rootView.findViewById(R.id.imgview);
    }

    private void setupVideoView() {
        videoView.setVideoPath(link);
        MediaController mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoView);//Neo media controler vào videoview
        videoView.setMediaController(mediaController);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(getContext(), "Sucessfully", Toast.LENGTH_SHORT).show();
            }
        });
        videoView.start();

    }

    @Override
    public void callback(String link) {
        if (imageView != null) {
            new DowloadHinhTask(imageView).execute(link);
        }
    }
}
