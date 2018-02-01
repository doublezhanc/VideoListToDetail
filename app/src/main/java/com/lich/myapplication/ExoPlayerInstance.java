package com.lich.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by Administrator on 2018/1/30.
 */

public class ExoPlayerInstance {
    private Context mContext;
    SimpleExoPlayer player;

    private static ExoPlayerInstance instance;

    public static ExoPlayerInstance getInstance(Context context){
        if (instance == null){
            instance = new ExoPlayerInstance(context);
        }
        return instance;
    }

    private ExoPlayerInstance(Context context) {
        super();
        mContext = context.getApplicationContext();
        createPlayer();
    }

    private void createPlayer() {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

// 2. Create the player
        player = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector);
    }

    public void prepareHlsUrl(String urlString) {
        Handler mainHandler = new Handler();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
// Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                Util.getUserAgent(mContext, "com.lich.myapplication"), bandwidthMeter);
// This is the MediaSource representing the media to be played.
//        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(Uri.parse(urlString));
        MediaSource videoSource = new HlsMediaSource(Uri.parse(urlString), dataSourceFactory, mainHandler, null);
// Prepare the player with the source.
        player.setPlayWhenReady(true);
        player.prepare(videoSource);
    }

    public void prepareMp4Url(String url){
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
// Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                Util.getUserAgent(mContext, "com.lich.myapplication"), bandwidthMeter);
// This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(url));
//        MediaSource videoSource = new HlsMediaSource(Uri.parse(urlString), dataSourceFactory, mainHandler, null);
// Prepare the player with the source.
        player.setPlayWhenReady(true);
        player.prepare(videoSource);
    }

}
