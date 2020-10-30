package com.decay.interview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.widget.Button
import com.decay.interview.fragment.DialogDemoActivity
import com.decay.interview.fragment.EditNameDialogFragment

import com.decay.interview.handler.HandlerJavaActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
        testPair()
        findViewById<Button>(R.id.btn_start).setOnClickListener {
            var intent = Intent(this, DialogDemoActivity::class.java)
            startActivity(intent)
        }
    }
    private fun test(){
        var sparseArray: SparseArray<String> = SparseArray<String>(10)
        sparseArray.put(1,"hello")
        sparseArray.put(2,"hello1")
        sparseArray.put(3,"hello2")
        sparseArray.put(4,"hello3")
        Log.d("sparseArray",sparseArray.toString())
    }
    private fun testPair(){
        var pair:Pair<Int,String> = Pair<Int,String>(1,"test")
        pair.copy(2,"test2")
        pair.copy(3,"test3")
        pair.copy(4,"test4")
        pair.copy(5,"test5")
        Log.d("pair",pair.toString())
    }
}