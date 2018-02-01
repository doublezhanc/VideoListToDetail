package com.lich.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2018/1/31.
 */

public class VideoFragmentActivity extends AppCompatActivity {
    VideoDetailFragment detailFragment;
    VideoListFragment listFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_fragment_activity);

        listFragment = new VideoListFragment();
        detailFragment = new VideoDetailFragment();

        FrameLayout container = findViewById(R.id.container_layout);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_layout, listFragment);
        ft.show(listFragment);
        ft.commitAllowingStateLoss();

    }


}
