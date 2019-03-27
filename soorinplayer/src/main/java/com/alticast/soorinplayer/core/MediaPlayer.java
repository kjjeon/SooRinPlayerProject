package com.alticast.soorinplayer.core;

import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.view.SurfaceHolder;

public interface MediaPlayer {
    int playback(SurfaceHolder surfaceHolder, int rawResourceId); //for test (RAW reousrce)
    int playback(SurfaceHolder surfaceHolder, Uri uri);
    int playback(SurfaceTexture surfaceTexture);
    int pause();
    int resume();
    int stop();
    int release();
    boolean isPlaying();
}
