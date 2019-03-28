package com.alticast.soorynplayerproject.video

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.Log
import com.alticast.soorynplayer.api.SooRynPlayer

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class VideoRender constructor(
    private val context: Context,
    private val sooRynPlayer: SooRynPlayer
) : GLSurfaceView.Renderer {
    private lateinit var directVideo: DirectVideo
    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        Log.d(TAG, "onSurfaceChanged")
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        Log.d(TAG, "onSurfaceCreated")
        sooRynPlayer.playback()
        directVideo = DirectVideo()
    }


    override fun onDrawFrame(gl: GL10?) {
        synchronized(this) {
            //            Log.d(TAG,"onDrawFrame")
            sooRynPlayer.videoTexture.updateTexture()

//            GLES20.glClearColor(0.0f, 1.0f, 0.0f, 1.0f) //색상 설정
//            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT or GLES20.GL_COLOR_BUFFER_BIT) //설정한 색상값으로 지우기 (컬러 버퍼와 깊이 버퍼)
            directVideo.draw(sooRynPlayer.videoTexture.videoMatrix)

        }
    }

    companion object {
        private const val TAG = "VideoRender"
    }
}