package com.lich.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;

/**
 * Created by Administrator on 2018/1/31.
 */

public class DetailPlayerActivity extends AppCompatActivity {

    ExoPlayerInstance mPlayer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.detail_player_layout);
        mPlayer = ExoPlayerInstance.getInstance(getApplicationContext());
        TextureView videoView = findViewById(R.id.detail_player);
        mPlayer.player.setVideoTextureView(videoView);
        videoView.requestFocus();

    }

}
