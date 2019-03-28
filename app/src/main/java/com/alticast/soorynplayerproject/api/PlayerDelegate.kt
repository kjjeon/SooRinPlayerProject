package com.alticast.soorynplayerproject.api

import com.alticast.soorynplayer.api.SooRynPlayer
import com.alticast.soorynplayerproject.SooRynPlayerApp


object PlayerDelegate {

    private lateinit var sooRynPlayer: SooRynPlayer

    fun build(): SooRynPlayer = run {
        if(!::sooRynPlayer.isInitialized) {
            sooRynPlayer = SooRynPlayer(SooRynPlayerApp.context)
        }
        sooRynPlayer
    }
}

