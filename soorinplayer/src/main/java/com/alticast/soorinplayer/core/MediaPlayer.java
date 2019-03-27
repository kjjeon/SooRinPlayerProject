package com.alticast.soorinplayer.core;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.view.SurfaceHolder;

public interface MediaPlayer {
    int playback(Context context, SurfaceHolder surfaceHolder, int rawResourceId); //for test (RAW reousrce)
    int playback(Context context, SurfaceHolder surfaceHolder, Uri uri);
    int playback(Context context, SurfaceTexture surfaceTexture);
}
