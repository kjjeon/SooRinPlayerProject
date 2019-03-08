package com.alticast.soorinplayerproject

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import com.alticast.soorinplayer.api.SooRinPlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private val sooRinPlayer: SooRinPlayer = SooRinPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        surface_view.holder.addCallback(this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
          sooRinPlayer.playback(applicationContext,holder,R.raw.sbs)
//        sooRinPlayer.playback(applicationContext,holder,Uri.parse("rawresource" + ":///" + R.raw.sbs))
    }
}
