package com.decay.interview.fragment.test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.decay.interview.R

class MyActivity : AppCompatActivity(R.layout.activity_my_test) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            add<ParentFragment>(R.id.content)
        }
        Log.i("MyActivity", "supportFragmentManager $supportFragmentManager")
    }
}

class ParentFragment : Fragment(R.layout.fragment_parent) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.commit {
            add<ChildFragment>(R.id.content)
        }
        Log.i("ParentFragment", "parentFragmentManager $parentFragmentManager")
        Log.i("ParentFragment", "childFragmentManager $childFragmentManager")
    }
}

class ChildFragment : Fragment(R.layout.fragment_child) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("ChildFragment", "parentFragmentManager $parentFragmentManager")
        Log.i("ChildFragment", "childFragmentManager $childFragmentManager")
    }
}