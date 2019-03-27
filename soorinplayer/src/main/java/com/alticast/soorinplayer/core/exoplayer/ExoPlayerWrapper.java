package com.alticast.soorinplayer.core.exoplayer;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.alticast.soorinplayer.R;
import com.alticast.soorinplayer.core.MediaPlayer;
import com.alticast.soorinplayer.core.exoplayer.source.TsDataSourceFactory;
import com.alticast.soorinplayer.utils.Constants;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.*;
import com.google.android.exoplayer2.util.Util;

public class ExoPlayerWrapper implements MediaPlayer {
    private Context context;
    private SimpleExoPlayer player = null;

    public ExoPlayerWrapper(Context context) {
        this.context = context;
    }

    @Override
    public synchronized int playback(SurfaceHolder surfaceHolder, int rawResourceId) {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        //Create the player
        if(player != null) { player.release(); }
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        player.setVideoSurfaceHolder(surfaceHolder);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "SooRinPlayer"));

        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawResourceDataSource.buildRawResourceUri(rawResourceId));

        LoopingMediaSource loopingMediaSource = new LoopingMediaSource(videoSource,2);
        // Prepare the player with the source.
        player.prepare(loopingMediaSource);
        //auto start playing
        player.setPlayWhenReady(true);
        return Constants.SUCCESS;
    }

    @Override
    public synchronized int playback(SurfaceHolder surfaceHolder, Uri uri) {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        //Create the player
        if(player != null) { player.release(); }
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        player.setVideoSurfaceHolder(surfaceHolder);

        DataSource.Factory dataSourceFactory = new TsDataSourceFactory(context,TsDataSourceFactory.RAW_FILE_TYPE);
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri);
//        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(Uri.parse("mcast://233.18.158.206:5000"));

        LoopingMediaSource loopingMediaSource = new LoopingMediaSource(videoSource,2);
        // Prepare the player with the source.
        player.prepare(loopingMediaSource);
        //auto start playing
        player.setPlayWhenReady(true);
        return Constants.SUCCESS;
    }

    @Override
    public synchronized int playback(SurfaceTexture surfaceTexture) {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        //Create the player
        if(player != null) { player.release(); }
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        Surface surface = new Surface(surfaceTexture);
        player.setVideoSurface(surface);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "SooRinPlayer"));

        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.test));

        LoopingMediaSource loopingMediaSource = new LoopingMediaSource(videoSource,2);
        // Prepare the player with the source.
        player.prepare(loopingMediaSource);
        //auto start playing
        player.setPlayWhenReady(true);
        return Constants.SUCCESS;
    }

    @Override
    public synchronized int pause() {
        if(player == null) return Constants.ERROR;
        player.setPlayWhenReady(false);
        return Constants.SUCCESS;
    }

    @Override
    public int resume() {
        if(player == null) return Constants.ERROR;
        player.setPlayWhenReady(true);
        return Constants.SUCCESS;
    }

    @Override
    public synchronized int stop() {
        if(player == null) return Constants.ERROR;
        player.stop();
        return Constants.SUCCESS;
    }

    @Override
    public synchronized int release() {
        if(player == null) return Constants.ERROR;
        if(player.getPlaybackState() != Player.STATE_IDLE) {
            player.stop();
        }
        player.release();
        player = null;
        return Constants.SUCCESS;
    }

    @Override
    public boolean isPlaying() {
        return player != null
                && player.getPlaybackState() != Player.STATE_ENDED
                && player.getPlaybackState() != Player.STATE_IDLE
                && player.getPlayWhenReady();
    }

}
