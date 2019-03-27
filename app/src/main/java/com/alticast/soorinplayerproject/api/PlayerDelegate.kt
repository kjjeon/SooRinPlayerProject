package com.alticast.soorinplayerproject.api

import com.alticast.soorinplayer.api.SooRinPlayer
import com.alticast.soorinplayerproject.SooRInPlayerApp


object PlayerDelegate {

    private lateinit var sooRinPlayer: SooRinPlayer

    fun build(): SooRinPlayer = run {
        if(!::sooRinPlayer.isInitialized) {
            sooRinPlayer = SooRinPlayer(SooRInPlayerApp.context)
        }
        sooRinPlayer
    }
}

