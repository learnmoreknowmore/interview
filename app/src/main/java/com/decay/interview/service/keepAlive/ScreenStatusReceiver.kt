package com.decay.interview.service.keepAlive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

internal class ScreenStatusReceiver : BroadcastReceiver() {
    var SCREEN_ON = "android.intent.action.SCREEN_ON"
    var SCREEN_OFF = "android.intent.action.SCREEN_OFF"
    override fun onReceive(context: Context, intent: Intent) {
        if (SCREEN_ON == intent.action) {
            Log.w("ppp", "ppp-屏幕亮了")
        } else if (SCREEN_OFF == intent.action) {
            Log.w("ppp", "ppp-屏幕暗了")
        }
    }
}