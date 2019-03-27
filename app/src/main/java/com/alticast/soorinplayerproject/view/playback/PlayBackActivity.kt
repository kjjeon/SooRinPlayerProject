package com.alticast.soorinplayerproject.view.playback

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import com.alticast.soorinplayer.api.SooRinPlayer
import com.alticast.soorinplayerproject.R
import com.alticast.soorinplayerproject.VideoRender
import com.alticast.soorinplayerproject.api.PlayerDelegate
import com.alticast.soorinplayerproject.test.VideoRender2
import com.alticast.soorinplayerproject.view.base.BaseActivity
import com.alticast.soorinplayerproject.view.consumption.ConsumptionActivity
import kotlinx.android.synthetic.main.activity_playback.*

class PlayBackActivity : BaseActivity(), SurfaceHolder.Callback {

    private lateinit var viewModel: PlayBackViewModel
    private lateinit var sooRInPlayer: SooRinPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)
        viewModel = ViewModelProviders.of(this).get(PlayBackViewModel::class.java)
        sooRInPlayer = PlayerDelegate.build()
        exVideoRender1()
    }

    override fun onDestroy() {
        super.onDestroy()
        sooRInPlayer.release()
        viewModel.stop()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(ev?.action == MotionEvent.ACTION_UP){
            Intent(this, ConsumptionActivity::class.java).let {
                it.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                applicationContext.startActivity(it)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun exVideoRender1() {
        glsurface_view.setEGLContextClientVersion(2)
        glsurface_view.setRenderer(VideoRender(applicationContext, sooRInPlayer))
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
        if(!sooRInPlayer.isPlaying)
            sooRInPlayer.playback(holder, R.raw.vid_bigbuckbunny)
//        PlayerDelegate.sooRinPlayer.playback(applicationContext,holder,Uri.parse("rawresource" + ":///" + R.raw.sbs))
    }

    companion object {
        private const val TAG = "PlayBackActivity"
    }
}
