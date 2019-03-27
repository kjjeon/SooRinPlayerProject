package com.alticast.soorinplayerproject.view

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import com.alticast.soorinplayer.api.SooRinPlayer
import com.alticast.soorinplayerproject.R
import com.alticast.soorinplayerproject.VideoRender
import com.alticast.soorinplayerproject.test.VideoRender2
import com.alticast.soorinplayerproject.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), SurfaceHolder.Callback {

    private val sooRinPlayer: SooRinPlayer by lazy { SooRinPlayer(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //surface_view.holder.addCallback(this)

        exVideoRender1()
    }


    private fun exVideoRender1() {
        glsurface_view.setEGLContextClientVersion(2)
        glsurface_view.setRenderer(VideoRender(applicationContext, sooRinPlayer))
    }

    // mediaplayer를 이용한 샘플
    private fun exVideoRender2(){
        val videoRender2 = VideoRender2(applicationContext)
        val mediaPlayer = MediaPlayer()
        videoRender2.setMediaPlayer(mediaPlayer)
        try {
            val afd = resources.openRawResourceFd(R.raw.vid_bigbuckbunny)
            mediaPlayer.setDataSource(
                afd.fileDescriptor, afd.startOffset, afd.length
            )
            afd.close()
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
        }
        glsurface_view.setEGLContextClientVersion(2)
        glsurface_view.setRenderer(videoRender2)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        sooRinPlayer.playback(holder, R.raw.vid_bigbuckbunny)
//        sooRinPlayer.playback(applicationContext,holder,Uri.parse("rawresource" + ":///" + R.raw.sbs))
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
