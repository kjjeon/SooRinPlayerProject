package com.alticast.soorinplayerproject.view.consumption

import android.util.Log
import com.alticast.soorinplayer.api.SooRinPlayer
import com.alticast.soorinplayerproject.api.PlayerDelegate
import com.alticast.soorinplayerproject.view.base.BaseViewModel

class ConsumptionViewModel : BaseViewModel() {

    private var sooRinPlayer: SooRinPlayer = PlayerDelegate.build()

    fun doKeyHandle(key: String) {
        Log.d(TAG,"postion = $key")
        when (key) {
            "play" -> {
                sooRinPlayer.playback()
            }
            "pause" -> {
                sooRinPlayer.pause()
            }
            "resume" -> {
                sooRinPlayer.resume()
            }
            "stop" -> {
                sooRinPlayer.stop()
            }
            "release" -> {
                sooRinPlayer.release()
            }
        }
    }

    companion object {
        private const val TAG = "ConsumptionViewModel"
    }

}