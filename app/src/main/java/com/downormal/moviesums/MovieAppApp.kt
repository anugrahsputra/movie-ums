package com.downormal.moviesums

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieAppApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}