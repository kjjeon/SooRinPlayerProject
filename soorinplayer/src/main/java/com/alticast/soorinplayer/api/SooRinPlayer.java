package com.alticast.soorinplayer.api;

import android.content.Context;
import android.view.SurfaceHolder;
import com.alticast.soorinplayer.core.MediaPlayer;
import com.alticast.soorinplayer.core.player.ExoPlayerWrapper;

public class SooRinPlayer {
    public int play(Context context, SurfaceHolder surfaceHolder, int rawResourceId) {
        MediaPlayer mp = new ExoPlayerWrapper();
        mp.play(context, surfaceHolder, rawResourceId);
        return 0;
    }
}
