package com.decay.breakpad

import android.app.Application

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        CrashReport.init(this)
    }
}