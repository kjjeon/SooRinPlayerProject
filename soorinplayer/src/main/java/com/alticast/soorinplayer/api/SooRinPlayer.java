package com.alticast.soorinplayer.api;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.view.SurfaceHolder;
import com.alticast.soorinplayer.core.MediaPlayer;
import com.alticast.soorinplayer.core.VideoTexture;
import com.alticast.soorinplayer.core.exoplayer.ExoPlayerWrapper;
import com.alticast.soorinplayer.core.exoplayer.TextureHandle;
import com.alticast.soorinplayer.core.exoplayer.VideoTextureImpl;

public class SooRinPlayer implements VideoTexture {

    private Context context;
    private VideoTexture videoTexture;

    public SooRinPlayer(Context context) {
        this.context = context;
        videoTexture = new VideoTextureImpl();
    }

    public int playback(SurfaceHolder surfaceHolder, int rawResourceId) {
        MediaPlayer mp = new ExoPlayerWrapper();
        mp.playback(context, surfaceHolder, rawResourceId);
        return 0;
    }

    public int playback(SurfaceHolder surfaceHolder, Uri uri) {
        MediaPlayer mp = new ExoPlayerWrapper();
        mp.playback(context, surfaceHolder, uri);
        return 0;
    }

    public int playback(SurfaceTexture surfaceTexture) {
        MediaPlayer mp = new ExoPlayerWrapper();
        mp.playback(context, surfaceTexture);
        return 0;
    }

    public int playback() {
        MediaPlayer mp = new ExoPlayerWrapper();
        mp.playback(context, videoTexture.getSurfaceTexture());
        return 0;
    }


    @Override
    public SurfaceTexture getSurfaceTexture() {
        return videoTexture.getSurfaceTexture();
    }

    @Override
    public void release() {
        videoTexture.release();
    }

    @Override
    public boolean isPlaybackStarted() {
        return videoTexture.isPlaybackStarted();
    }

    @Override
    public void prepareForNewMovie() {
        videoTexture.prepareForNewMovie();
    }

    @Override
    public boolean updateTexture() {
        return videoTexture.updateTexture();
    }

    @Override
    public int getVideoTextureId() {
        return videoTexture.getVideoTextureId();
    }

    @Override
    public int getVideoTextureWidth() {
        return videoTexture.getVideoTextureWidth();
    }

    @Override
    public float[] getVideoMatrix() {
        return videoTexture.getVideoMatrix();
    }

    @Override
    public long getVideoTimestampNs() {
        return videoTexture.getVideoTimestampNs();
    }

    @Override
    public TextureHandle getTextureHandle() {
        return videoTexture.getTextureHandle();
    }

    @Override
    public TextureHandle getTextureHandleWithIdentity() {
        return videoTexture.getTextureHandleWithIdentity();
    }
}
