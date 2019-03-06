package com.alticast.soorinplayer.core;

import android.content.Context;
import android.view.SurfaceHolder;

public interface MediaPlayer {
    int play(Context context, SurfaceHolder surfaceHolder , int rawResourceId);
}
