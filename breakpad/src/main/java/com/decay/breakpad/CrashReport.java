package com.decay.breakpad;

import android.content.Context;

import java.io.File;

/**
 * @author 1
 */
public class CrashReport {
    static {
        System.loadLibrary("bugly");
    }

    public static void init(Context context) {
        //开启java监控
        Context applicationContext = context.getApplicationContext();
        CrashHandler.init(applicationContext);
        // 开启ndk监控
        File file = new File(context.getExternalCacheDir(), "native_crash");
        if (!file.exists()) {
            /**
             *   此时，如果出现NDK Crash，会在我们指定的目录： /sdcard/Android/Data/[packageName]/cache/native_crash 下生成NDK Crash信息文件。
             *         Crash解析
             *         采集到的Crash信息记录在minidump文件中。minidump是由微软开发的用于崩溃上传的文件格式。我
             *         们可以将此文件上传到服务器完成上报，但是此文件没有可读性可言，要将文件解析为可读的崩溃堆栈
             *         需要按照breakpad文档编译 minidump_stackwalk 工具，而Windows系统编译个人不会。不过好在，
             *         无论你是 Mac、windows还是ubuntu在 Android Studio 的安装目录下的 bin\lldb\bin 里面就存在一
             *         个对应平台的 minidump_stackwalk 。
             */
            file.mkdirs();
        }
        initBreakpad(file.getAbsolutePath());
    }

    /**
     * C++: Java_com_enjoy_crash_CrashReport_initBreakpad
     */

    private static native void initBreakpad(String path);

    /**
     * C++: Java_com_enjoy_crash_CrashReport_testNativeCrash
     */

    public static native void testNativeCrash();

    public static int testJavaCrash() {
        return 1 / 0;
    }
}
