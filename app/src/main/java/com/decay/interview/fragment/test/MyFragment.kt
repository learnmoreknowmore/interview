package com.decay.interview.fragment.test

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
// Caused by: androidx.fragment.app.Fragment$InstantiationException: Unable to instantiate fragment com.decay.interview.fragment.test.MyFragment: could not find Fragment constructor
//myFragment当有有参构造函数构造时 当配置文件修改，等需要重新初始化时候，系统不知道怎么构造MyFragment
class MyFragment constructor(var title: String) : Fragment() {
    companion object {
        @Volatile
        private var instance: MyFragment? = null
        fun getInstance(title: String): MyFragment {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = MyFragment(title = title)
                    }
                }
            }
            return instance!!
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //在fragment中拦截返回键
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
//                Toast.makeText(requireContext(),"拦截",Toast.LENGTH_SHORT).show()
//                requireActivity().finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}