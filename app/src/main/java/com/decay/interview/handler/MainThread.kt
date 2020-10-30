package com.decay.interview.handler

import android.os.Handler
import android.os.Looper
import androidx.annotation.NonNull

/**
 * 巧用Looper机制
 */
final class MainThread {
    private constructor()
    fun test(){
    }
    companion object {
        @JvmStatic
        private var mHandler: Handler = Handler(Looper.getMainLooper())

        @JvmStatic
        fun run(@NonNull runnable: Runnable) {
            if (isMainThread()) {
                runnable.run()
            } else {
                mHandler.post(runnable)
            }
        }

        @JvmStatic
        fun isMainThread(): Boolean {
            return Looper.myLooper() == Looper.getMainLooper()
        }
    }
}