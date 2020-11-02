package com.decay.interview.service.keepAlive

import android.app.Activity
import android.content.Context
import android.util.Log
import com.blankj.utilcode.util.ActivityUtils
import java.lang.ref.WeakReference

class ScreenManager private constructor(var context: Context) {
    private var mActivityReference: WeakReference<Activity>? = null

    companion object {
        private var instance: ScreenManager? = null
        fun getInstance(context: Context): ScreenManager {
            if (instance == null) {
                instance = ScreenManager(context)
            }
            return instance!!
        }
    }

    fun setActivity(activity: Activity) {
        mActivityReference = WeakReference<Activity>(activity)
    }

    fun startActivity() {
        LiveActivity.actionToLiveActivity(context)
    }

    fun finishActivity() {
        if (mActivityReference != null) {
            if (mActivityReference?.get() != null) {
                mActivityReference?.get()?.finish()
                Log.e("",mActivityReference?.get()?.localClassName!!)
            }
        }

    }
}