package com.decay.interview.service.keepAlive

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ActivityUtils
import com.decay.interview.R

class LiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "一像素activity已经创建")
        val window = window
        window.setGravity(Gravity.LEFT or Gravity.TOP)
        val params = window.attributes
        params.x = 0
        params.y = 0
        params.height = 1
        params.width = 1
        window.attributes = params
        mContext = this
        ScreenManager.getInstance(this).setActivity(this)
    }

    companion object {
        private var mContext: AppCompatActivity? = null
        var TAG: String = LiveActivity::class.java.simpleName

        @JvmStatic
        fun actionToLiveActivity(context: Context) {
            val intent = Intent(context, LiveActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "一像素activity已经销毁")
    }
}