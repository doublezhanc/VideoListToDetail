package com.lich.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/1/31.
 */

public class VideoDetailFragment extends Fragment {
    private View mRootView;
    ExoPlayerInstance mPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.detail_player_layout, null);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPlayer = ExoPlayerInstance.getInstance(getActivity().getApplicationContext());
        TextureView videoView = mRootView.findViewById(R.id.detail_player);
//        videoView.setPlayer(mPlayer.player);
//        videoView.setPlayer(mPlayer.player);
        mPlayer.player.setVideoTextureView(videoView);
        videoView.requestFocus();
    }
}
