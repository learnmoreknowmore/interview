package com.decay.interview.service.keepAlive

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log

class ScreenBroadcastListener {
    //事件监听
    interface ScreenStateListener {
        fun onScreenOn()
        fun onScreenOff()
    }

    private var mListener: ScreenStateListener? = null
    private var mContext: Context? = null
    private var mBroadcastReceiver: ScreenBroadcastReceiver? = null


    constructor(context: Context) {
        mContext = context.applicationContext
        mBroadcastReceiver = ScreenBroadcastReceiver()
    }

    inner class ScreenBroadcastReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            var action: String? = intent.action
            if (action.equals(Intent.ACTION_SCREEN_ON)) {
                Log.w("ppp","ppp-屏幕亮了");
                mListener?.onScreenOn()
            } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                Log.w("ppp","ppp-屏幕暗了");
                mListener?.onScreenOff()
            }
        }
    }

    fun registerListener(listener: ScreenStateListener) {
        this.mListener = listener
        registerListener()
    }

    private fun registerListener() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF)
        intentFilter.addAction(Intent.ACTION_SCREEN_ON)
        mContext?.registerReceiver(mBroadcastReceiver, intentFilter)
    }
}