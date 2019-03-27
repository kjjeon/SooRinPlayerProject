package com.alticast.soorinplayer.api;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.view.SurfaceHolder;
import com.alticast.soorinplayer.core.MediaPlayer;
import com.alticast.soorinplayer.core.gl.VideoTexture;
import com.alticast.soorinplayer.core.exoplayer.ExoPlayerWrapper;
import com.alticast.soorinplayer.core.gl.TextureHandle;
import com.alticast.soorinplayer.core.gl.impl.VideoTextureImpl;

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

    /**
     * Gets the SurfaceTexture that receives video frames.
     *
     * @return The SurfaceTexture that receives video frames.
     */
    @Override
    public SurfaceTexture getSurfaceTexture() {
        return videoTexture.getSurfaceTexture();
    }

    /** Releases the video texture, deleting it's GL ID. */
    @Override
    public void release() {
        videoTexture.release();
    }

    /**
     * Gets whether playback has started.
     *
     * @return Whether playback has started.
     */
    @Override
    public boolean isPlaybackStarted() {
        return videoTexture.isPlaybackStarted();
    }

    /** Resets playback state. */
    @Override
    public void prepareForNewMovie() {
        videoTexture.prepareForNewMovie();
    }

    /**
     * Retrieves the latest video frame and stores it in a SurfaceTexture.
     *
     * @return whether the SurfaceTexture was updated.
     */
    @Override
    public boolean updateTexture() {
        return videoTexture.updateTexture();
    }

    /** Returns the texture ID for the video texture. */
    @Override
    public int getVideoTextureId() {
        return videoTexture.getVideoTextureId();
    }

    @Override
    public int getVideoTextureWidth() {
        return videoTexture.getVideoTextureWidth();
    }

    /** Returns the transformation matrix for the video. */
    @Override
    public float[] getVideoMatrix() {
        return videoTexture.getVideoMatrix();
    }

    /**
     * Gets a TextureHandle wrapping the video frame.
     *
     * @return A TextureHandle wrapping the video frame with the current transform matrix.
     */
    @Override
    public long getVideoTimestampNs() {
        return videoTexture.getVideoTimestampNs();
    }

    /**
     * Gets a TextureHandle wrapping the video frame.
     *
     * @return A TextureHandle wrapping the video frame with the no transformation.
     */
    @Override
    public TextureHandle getTextureHandle() {
        return videoTexture.getTextureHandle();
    }

    @Override
    public TextureHandle getTextureHandleWithIdentity() {
        return videoTexture.getTextureHandleWithIdentity();
    }
}
