package com.mostafiz.cmed.restapitask.di

import android.app.Application
import android.content.SharedPreferences
import androidx.annotation.Keep
import dagger.hilt.android.HiltAndroidApp
@Keep
@HiltAndroidApp
class AppInstance :Application(){
    override fun onCreate() {
        super.onCreate()
    }
}