package com.alticast.soorinplayer.core.gl;

import android.graphics.SurfaceTexture;

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
