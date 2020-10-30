package com.decay.interview.handler

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class HandlerActivity : AppCompatActivity() {
    private var handler0: Handler? = null
    private var handler1: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler0 = Handler()
        Thread { handler1 = Handler() }.start()
    }
}