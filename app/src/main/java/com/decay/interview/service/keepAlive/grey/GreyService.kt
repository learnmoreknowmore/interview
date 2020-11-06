package com.decay.interview.service.keepAlive.grey

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.widget.Toast
import com.blankj.utilcode.util.NotificationUtils


class GreyService : Service() {
    private val GRAY_SERVICE_ID = 1001
    private var notificationChannel: NotificationChannel? = null
    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "服务已经停止", Toast.LENGTH_LONG).show()
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "服务已经启动", Toast.LENGTH_LONG).show()
        if (android.os.Build.VERSION.SDK_INT < 18) {
            startForeground(GRAY_SERVICE_ID, Notification()) //API < 18 ，此方法能有效隐藏 Notification 上的图标
        } else {
            var innerIntent = Intent(this, InnerService()::class.java)
            startService(innerIntent)
            startForeground(GRAY_SERVICE_ID, null)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    inner class InnerService : Service() {
        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            startForeground(GRAY_SERVICE_ID, Notification())
            stopForeground(true)
            stopSelf()
            return super.onStartCommand(intent, flags, startId)
        }

        override fun onBind(intent: Intent?): IBinder? {
            return null
        }
    }
    fun buildNotification(){
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel!!.enableLights(true)
            notificationChannel!!.lightColor = Color.RED
            notificationChannel!!.setShowBadge(true)
            notificationChannel!!.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager?.createNotificationChannel(notificationChannel!!)
        }
    }
    companion object{
        val channelId:String = "channelId_test"
        val channelName:String = "channelId_name"
    }
}