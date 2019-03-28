package com.alticast.soorinplayerproject.api

import com.alticast.soorynplayer.api.SooRynPlayer
import com.alticast.soorinplayerproject.SooRInPlayerApp


object PlayerDelegate {

    private lateinit var sooRynPlayer: SooRynPlayer

    fun build(): SooRynPlayer = run {
        if(!::sooRynPlayer.isInitialized) {
            sooRynPlayer = SooRynPlayer(SooRInPlayerApp.context)
        }
        sooRynPlayer
    }
}

