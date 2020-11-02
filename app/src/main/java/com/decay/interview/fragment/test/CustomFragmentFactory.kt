package com.decay.interview.fragment.test

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class CustomFragmentFactory(private val arg: String) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        if (className == MyFragment::class.java.name) {
            return MyFragment(arg)
        }
        return super.instantiate(classLoader, className)
    }
}