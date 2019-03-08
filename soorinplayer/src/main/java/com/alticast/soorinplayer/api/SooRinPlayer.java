package com.alticast.soorinplayer.api;

import android.content.Context;
import android.net.Uri;
import android.view.SurfaceHolder;
import com.alticast.soorinplayer.core.MediaPlayer;
import com.alticast.soorinplayer.core.exoplayer.ExoPlayerWrapper;

public class SooRinPlayer {
    public int playback(Context context, SurfaceHolder surfaceHolder, int rawResourceId) {
        MediaPlayer mp = new ExoPlayerWrapper();
        mp.playback(context, surfaceHolder, rawResourceId);
        return 0;
    }

    public int playback(Context context, SurfaceHolder surfaceHolder, Uri uri) {
        MediaPlayer mp = new ExoPlayerWrapper();
        mp.playback(context, surfaceHolder, uri);
        return 0;
    }
}
