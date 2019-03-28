package com.alticast.soorynplayerproject.view.consumption

import android.util.Log
import com.alticast.soorynplayer.api.SooRynPlayer
import com.alticast.soorynplayerproject.api.PlayerDelegate
import com.alticast.soorynplayerproject.view.base.BaseViewModel

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