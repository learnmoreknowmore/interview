package com.decay.jni

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * JNI Types and Data Structures
 * Java Type     Native Type     Description        对应java VM中的类型签名
 * boolean        jboolean       unsigned 8 bits         Z
 * byte           jbyte          signed 8 bits           B
 * char           jchar          unsigned 16 bits        C
 * short          jshort         signed 16 bits          S
 * int            jint           signed 32 bits          I
 * long           jlong          signed 64 bits          J
 * float          jfloat         signed 32 bits          F
 * double         jdouble        signed 64 bits          D
 * void           void           N/A
 * fully-qualified-class                                 L fully-qualified-class
 * type[]                                                [type
 * method type                                           ( arg-types ) ret-type
 * 例如：
 * java中的方法:对应java vm中签名类型
 * long f(int n,String s,int[] arr) => (ILjava/lang/String;[I)J
 *
 * Java VM Type Signatures
 * The following definition is provided for convenience.
 * #define JNI_FALSE  0
 * #define JNI_TRUE   1
 */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}