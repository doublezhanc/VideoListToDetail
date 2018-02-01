package com.lich.myapplication;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoItemHolder> {
    List<String> mVideos = new ArrayList<>();
    ExoPlayerInstance mExoPlayer;
    private Activity mActivity;
    private VideoItemHolder mCurrentHolder;


    public VideoListAdapter(Activity activity) {
        super();
        mActivity = activity;
        mExoPlayer = ExoPlayerInstance.getInstance(activity);

        mVideos.add("http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8");
        mVideos.add("http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8");
        mVideos.add("http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8");
        mVideos.add("http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8");
        mVideos.add("http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8");
        mVideos.add("http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8");
    }

    @Override
    public VideoItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, null);
        return new VideoItemHolder(v);
    }

    @Override
    public void onBindViewHolder(final VideoItemHolder holder, final int position) {
        holder.mVideoName.setText("第 " + position + " 视频");
        holder.mVideoView.setVisibility(View.GONE);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!holder.isPlaying) {
                    holder.isPlaying = true;
                    holder.mVideoView.setVisibility(View.VISIBLE);
                    holder.mPosterImg.setVisibility(View.GONE);

                    mExoPlayer.player.setVideoTextureView(holder.mVideoView);

                    holder.mVideoView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
                        @Override
                        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                            mExoPlayer.player.setVideoSurface(new Surface(surfaceTexture));
                            mExoPlayer.prepareHlsUrl(mVideos.get(position));
                        }

                        @Override
                        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

                        }

                        @Override
                        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                            return false;
                        }

                        @Override
                        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

                        }
                    });

                    /*mExoPlayer.player.setVideoSurfaceView(holder.mVideoView);
                    mExoPlayer.player.setVideoSurface(holder.mVideoView.getHolder().getSurface());
                    holder.mVideoView.getHolder().addCallback(new SurfaceHolder.Callback() {
                        @Override
                        public void surfaceCreated(SurfaceHolder surfaceHolder) {
                            Log.d("LichKing","created");
                            mExoPlayer.prepareUrl(mVideos.get(position));
                        }

                        @Override
                        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                        }

                        @Override
                        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

                        }
                    });*/
//                    holder.mVideoView.setVideoURI(Uri.parse(mVideos.get(position)));
//                    holder.mVideoView.start();
//                    holder.mVideoView.setPlayer(mExoPlayer.player);
//                    mExoPlayer.prepareUrl(mVideos.get(position));
                } else {
//                    Bundle bundle = new Bundle();
                    if (mActivity instanceof VideoListActivity) {
                        mCurrentHolder = holder;
                        Intent intent = new Intent(mActivity, DetailPlayerActivity.class);

//                    bundle.putString("key2", mVideos.get(position));
//                    bundle.putBundle("key1", ActivityOptions.makeSceneTransitionAnimation(mActivity, holder.mVideoView, "video").toBundle());
                        mActivity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mActivity, holder.mVideoView, "video").toBundle());
                    } else if (mActivity instanceof VideoFragmentActivity) {
                        VideoFragmentActivity vacitivity = ((VideoFragmentActivity)mActivity);
                        ViewCompat.setTransitionName(holder.mVideoView, "video");
                        FragmentTransaction ft = vacitivity.getSupportFragmentManager().beginTransaction();
                        ft.add(R.id.container_layout, vacitivity.detailFragment);
                        ft.addSharedElement(holder.mVideoView, "video");
                        ft.hide(vacitivity.listFragment);
                        ft.show(vacitivity.detailFragment);
                        ft.commitAllowingStateLoss();

                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    public void resumePlay() {
        if (mCurrentHolder != null && mCurrentHolder.isPlaying) {
            mExoPlayer.player.setVideoTextureView(mCurrentHolder.mVideoView);
        }
    }

    public class VideoItemHolder extends RecyclerView.ViewHolder {
        boolean isPlaying;
        TextureView mVideoView;
        TextView mVideoName;
        ImageView mPosterImg;

        public VideoItemHolder(View itemView) {
            super(itemView);
            mVideoName = itemView.findViewById(R.id.video_name);
            mVideoView = itemView.findViewById(R.id.video_item_view);
            mPosterImg = itemView.findViewById(R.id.poster_img);
        }
    }
}
