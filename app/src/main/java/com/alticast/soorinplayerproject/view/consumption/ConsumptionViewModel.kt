package com.alticast.soorinplayerproject.view.consumption

import android.util.Log
import com.alticast.soorynplayer.api.SooRynPlayer
import com.alticast.soorinplayerproject.api.PlayerDelegate
import com.alticast.soorinplayerproject.view.base.BaseViewModel

class ConsumptionViewModel : BaseViewModel() {

    private var sooRynPlayer: SooRynPlayer = PlayerDelegate.build()

    fun doKeyHandle(key: String) {
        Log.d(TAG,"postion = $key")
        when (key) {
            "play" -> {
                sooRynPlayer.playback()
            }
            "pause" -> {
                sooRynPlayer.pause()
            }
            "resume" -> {
                sooRynPlayer.resume()
            }
            "stop" -> {
                sooRynPlayer.stop()
            }
            "release" -> {
                sooRynPlayer.release()
            }
        }
    }

    companion object {
        private const val TAG = "ConsumptionViewModel"
    }

}