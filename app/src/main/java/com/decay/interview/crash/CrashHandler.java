package com.decay.interview.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author 1
 * 捕获JAVA异常
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String FILE_NAME_SUFFIX = ".trace";
    private static Context mContext;
    private static Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;

    private CrashHandler() {
    }

    public void init(@NonNull Context context) {
        //系统默认是runtimeInit#KillApplicationHandler
        mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context.getApplicationContext();
    }

    /**
     * 当程序有未捕获异常
     *
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        try {
            //自己处理保存到本地
            File file = dealException(t, e);
            //上传到服务器todo

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            //交给系统默认去处理
            if (mDefaultUncaughtExceptionHandler != null) {
                mDefaultUncaughtExceptionHandler.uncaughtException(t, e);
            }
        }
    }

    /**
     * 导出异常信息到sd卡
     */
    private File dealException(@NonNull Thread t, @NonNull Throwable e) throws Exception {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        File fileDir = new File(mContext.getExternalCacheDir().getAbsolutePath(), "crash_info");
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        File crashFile = new File(fileDir, time + FILE_NAME_SUFFIX);
        //往文件写入数据
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(crashFile)));
        pw.println(time);
        pw.println("thread: " + t.getName());
        pw.println(getPhoneInfo());
        e.printStackTrace(pw);
        pw.close();
        return crashFile;
    }

    private String getPhoneInfo() throws PackageManager.NameNotFoundException {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo pi = packageManager.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        StringBuilder sb = new StringBuilder();
        //app版本
        sb.append("App version: ");
        sb.append(pi.versionName);
        sb.append("_");
        sb.append(pi.versionCode + "\n");

        //android 版本号
        sb.append("OS version: ");
        sb.append(Build.VERSION.RELEASE);
        sb.append("_");
        sb.append(Build.VERSION.SDK_INT + "\n");

        //手机制造商
        sb.append("Vendor: ");
        sb.append(Build.MANUFACTURER + "\n");
        //手机型号
        sb.append("Model: ");
        sb.append(Build.MODEL + "\n");
        //cpu架构
        sb.append("CPU: ");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sb.append(Arrays.toString(Build.SUPPORTED_ABIS));
        } else {
            sb.append(Build.CPU_ABI);
        }
        return sb.toString();
    }
}
