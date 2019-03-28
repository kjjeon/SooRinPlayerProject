package com.alticast.soorynplayer.api;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.view.SurfaceHolder;
import com.alticast.soorynplayer.core.MediaPlayer;
import com.alticast.soorynplayer.core.exoplayer.ExoPlayerWrapper;
import com.alticast.soorynplayer.core.gl.VideoTexture;
import com.alticast.soorynplayer.core.gl.impl.VideoTextureImpl;

public class SooRynPlayer implements MediaPlayer
{

    private MediaPlayer mediaPlayer;
    private VideoTexture videoTexture;

    public SooRynPlayer(Context context) {
        videoTexture = new VideoTextureImpl();
        mediaPlayer = new ExoPlayerWrapper(context);
    }

    @Override
    public int playback(SurfaceHolder surfaceHolder, int rawResourceId) {
        return mediaPlayer.playback(surfaceHolder, rawResourceId);
    }

    @Override
    public int playback(SurfaceHolder surfaceHolder, Uri uri) {
        return mediaPlayer.playback(surfaceHolder, uri);
    }

    @Override
    public int playback(SurfaceTexture surfaceTexture) {
        return mediaPlayer.playback(surfaceTexture);
    }

    @Override
    public int pause() {
        return mediaPlayer.pause();
    }

    @Override
    public int resume() { return mediaPlayer.resume(); }

    @Override
    public int stop() {
        return mediaPlayer.stop();
    }

    @Override
    public int release() {
        return mediaPlayer.release();
    }

    @Override
    public boolean isPlaying() { return mediaPlayer.isPlaying(); }

    public int playback() {
        return mediaPlayer.playback(videoTexture.getSurfaceTexture());
    }

    public VideoTexture getVideoTexture() {
        return videoTexture;
    }

}
