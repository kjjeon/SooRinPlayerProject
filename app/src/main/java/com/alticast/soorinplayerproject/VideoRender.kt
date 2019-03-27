package com.alticast.soorinplayerproject

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.util.Log
import com.alticast.soorinplayer.api.SooRinPlayer
import com.alticast.soorinplayer.core.exoplayer.GLUtil
import com.alticast.soorinplayer.core.exoplayer.GLUtil.FLOAT_SIZE_BYTES
import com.alticast.soorinplayer.core.exoplayer.TextureHandle
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class VideoRender constructor(private val context: Context,
                              private val sooRinPlayer: SooRinPlayer)
    : GLSurfaceView.Renderer
{
      private lateinit var directVideo:DirectVideo
//    private val TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 5 * FLOAT_SIZE_BYTES
//    private val TRIANGLE_VERTICES_DATA_POS_OFFSET = 0
//    private val TRIANGLE_VERTICES_DATA_UV_OFFSET = 3
//    private var mProgram: Int = 0
//    private var mTextureID: Int = 0
//    private var muMVPMatrixHandle: Int = 0
//    private var muSTMatrixHandle: Int = 0
//    private var maPositionHandle: Int = 0
//    private var maTextureHandle: Int = 0
//
//    private val mMVPMatrix = FloatArray(16)
//    private val mSTMatrix = FloatArray(16)
//
//    private val mTriangleVerticesData = floatArrayOf(
//        // X, Y, Z, U, V
//        -1.0f, -1.0f, 0f, 0f, 0f, 1.0f, -1.0f, 0f, 1f, 0f, -1.0f, 1.0f, 0f, 0f, 1f, 1.0f, 1.0f, 0f, 1f, 1f
//    )
//
//    private val mTriangleVertices: FloatBuffer
//
//    private val mVertexShader = "uniform mat4 uMVPMatrix;\n" +
//            "uniform mat4 uSTMatrix;\n" +
//            "attribute vec4 aPosition;\n" +
//            "attribute vec4 aTextureCoord;\n" +
//            "varying vec2 vTextureCoord;\n" +
//            "void main() {\n" +
//            "  gl_Position = uMVPMatrix * aPosition;\n" +
//            "  vTextureCoord = (uSTMatrix * aTextureCoord).xy;\n" +
//            "}\n"
//
//    private val mFragmentShader = (
//            "#extension GL_OES_EGL_image_external : require\n" +
//                    "precision mediump float;\n" +
//                    "varying vec2 vTextureCoord;\n" +
//                    "uniform samplerExternalOES sTexture;\n" +
//                    "void main() {\n" +
//                    "  gl_FragColor = texture2D(sTexture, vTextureCoord);\n" +
//                    "}\n")
//

    init {
//        mTriangleVertices = ByteBuffer.allocateDirect(
//            mTriangleVerticesData.size * FLOAT_SIZE_BYTES
//        )
//            .order(ByteOrder.nativeOrder()).asFloatBuffer()
//        mTriangleVertices.put(mTriangleVerticesData).position(0)
//
//        Matrix.setIdentityM(mSTMatrix, 0)
    }
    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        Log.d(TAG,"onSurfaceChanged")
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        Log.d(TAG,"onSurfaceCreated")
        sooRinPlayer.playback()
        directVideo = DirectVideo()
    }


    override fun onDrawFrame(gl: GL10?) {
        synchronized(this) {
//            Log.d(TAG,"onDrawFrame")
            sooRinPlayer.updateTexture()

//            GLES20.glClearColor(0.0f, 1.0f, 0.0f, 1.0f) //색상 설정
//            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT or GLES20.GL_COLOR_BUFFER_BIT) //설정한 색상값으로 지우기 (컬러 버퍼와 깊이 버퍼)
            directVideo.draw()

        }
    }
    companion object {
        private const val TAG = "VideoRender"
    }
}