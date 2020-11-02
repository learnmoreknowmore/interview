package com.decay.interview

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.decay.interview.fragment.test.CustomFragmentFactory
import com.decay.interview.fragment.test.MyFragment
import com.decay.interview.service.keepAlive.*
import com.decay.interview.service.keepAlive.ScreenStatusReceiver


class MainActivity : AppCompatActivity() {
    private val customFragmentFactory = CustomFragmentFactory(dependency())
    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = customFragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        test()
//        testPair()
        //testKeepAlive()
        startAliveService()
        // testFragment()
        //registerScreenStatusReceiver()
        findViewById<Button>(R.id.btn_start).setOnClickListener {
//            var intent = Intent(this, MyActivity::class.java)
//           // startActivity(intent)
            //registerScreenStatusReceiver()
            //startService()

        }
    }

    private fun registerScreenStatusReceiver() {
        var mScreenStatusReceiver = ScreenStatusReceiver()
        val screenStatusIF = IntentFilter()
        screenStatusIF.addAction(Intent.ACTION_SCREEN_ON)
        screenStatusIF.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(mScreenStatusReceiver, screenStatusIF)
    }
    // Method to start the service
    private fun startService() {
        startService(Intent(baseContext, LiveService::class.java))
    }

    // Method to stop the service
    private fun stopService() {
        stopService(Intent(baseContext, LiveService::class.java))
    }

    private fun startAliveService() {
        LiveService.toLiveService(this)
    }

//    private fun testKeepAlive() {
//        var screenManager: ScreenManager = ScreenManager.getInstance(this)
//        var mScreenBroadcastListener = ScreenBroadcastListener(this)
//        mScreenBroadcastListener.registerListener(object :
//                ScreenBroadcastListener.ScreenStateListener {
//            override fun onScreenOn() {
//                screenManager?.finishActivity()
//            }
//
//            override fun onScreenOff() {
//                screenManager.startActivity()
//            }
//        })
//    }

    private fun dependency(): String {
        return "test"
    }

    private fun testFragment() {
        val testFragment = MyFragment.getInstance(dependency())
        supportFragmentManager.commit {
            // add<MyFragment>(R.id.content)
            add(testFragment, testFragment::class.java.canonicalName)
        }
    }

    private fun test() {
        var sparseArray: SparseArray<String> = SparseArray<String>(10)
        sparseArray.put(1, "hello")
        sparseArray.put(2, "hello1")
        sparseArray.put(3, "hello2")
        sparseArray.put(4, "hello3")
        Log.d("sparseArray", sparseArray.toString())
    }

    private fun testPair() {
        var pair: Pair<Int, String> = Pair<Int, String>(1, "test")
        pair.copy(2, "test2")
        pair.copy(3, "test3")
        pair.copy(4, "test4")
        pair.copy(5, "test5")
        Log.d("pair", pair.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        //stopService()
    }
}