package com.alticast.soorinplayer.core.exoplayer;

import android.content.Context;
import android.view.SurfaceHolder;
import com.alticast.soorinplayer.core.MediaPlayer;
import com.alticast.soorinplayer.source.RawDataSource;
import com.alticast.soorinplayer.source.TsDataSourceFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.*;

public class ExoPlayerWrapper implements MediaPlayer {

    @Override
    public int playback(Context context, SurfaceHolder surfaceHolder, int rawResourceId) {
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        //Create the player
        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        player.setVideoSurfaceHolder(surfaceHolder);

        DataSource.Factory dataSourceFactory = new TsDataSourceFactory(context,TsDataSourceFactory.RAW_FILE_TYPE);
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawDataSource.buildRawResourceUri(rawResourceId));

        LoopingMediaSource loopingMediaSource = new LoopingMediaSource(videoSource,2);
        // Prepare the player with the source.
        player.prepare(loopingMediaSource);
        //auto start playing
        player.setPlayWhenReady(true);
        return 0;

        //original source
//        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
//        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
//        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
//
//        //Create the player
//        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
//        player.setVideoSurfaceHolder(surfaceHolder);
//
//        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
//                Util.getUserAgent(context, "SooRinPlayer"));
//
//        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(RawResourceDataSource.buildRawResourceUri(rawResourceId));
//
//        LoopingMediaSource loopingMediaSource = new LoopingMediaSource(videoSource,2);
//        // Prepare the player with the source.
//        player.prepare(loopingMediaSource);
//        //auto start playing
//        player.setPlayWhenReady(true);
//        return 0;
    }

    @Override
    public int playback(Context context, SurfaceHolder surfaceHolder) {
        return 0;
    }
}
