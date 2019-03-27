package com.alticast.soorinplayer.core.gl;

import android.graphics.SurfaceTexture;
import com.alticast.soorinplayer.core.gl.TextureHandle;

public interface VideoTexture {
    SurfaceTexture getSurfaceTexture();
    void release();
    boolean isPlaybackStarted();
    void prepareForNewMovie();
    boolean updateTexture();
    int getVideoTextureId();
    int getVideoTextureWidth();
    float[] getVideoMatrix();
    long getVideoTimestampNs();
    TextureHandle getTextureHandle();
    TextureHandle getTextureHandleWithIdentity();
}
