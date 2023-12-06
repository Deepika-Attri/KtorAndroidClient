package com.example.ktorandroidclient

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class KtorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}