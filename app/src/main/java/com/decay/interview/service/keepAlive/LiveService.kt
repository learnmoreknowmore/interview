package com.decay.interview.service.keepAlive

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast


class LiveService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "服务已经启动", Toast.LENGTH_LONG).show()
        var screenManager: ScreenManager = ScreenManager.getInstance(this.baseContext)
        var mScreenBroadcastListener = ScreenBroadcastListener(this)
        mScreenBroadcastListener.registerListener(object :
                ScreenBroadcastListener.ScreenStateListener {
            override fun onScreenOn() {
                screenManager?.finishActivity()
            }

            override fun onScreenOff() {
                screenManager.startActivity()
            }
        })
        return START_REDELIVER_INTENT
    }

    companion object {
        fun toLiveService(context: Context) {
            var intent: Intent = Intent(context, LiveService::class.java)
            context.startService(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "服务已经停止", Toast.LENGTH_LONG).show()
    }
}