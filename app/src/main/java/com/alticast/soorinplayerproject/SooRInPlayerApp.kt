package com.alticast.soorinplayerproject

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SooRInPlayerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

}