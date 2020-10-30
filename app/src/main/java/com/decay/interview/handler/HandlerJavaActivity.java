package com.decay.interview.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.decay.interview.R;

public class HandlerJavaActivity extends AppCompatActivity {
    private Handler mHandler0;
    private Handler mHandler1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mHandler0 = new Handler();
        toastInThreadCorrect();
        new Thread(() -> {
            Looper.prepare();
            Log.d("looper", String.valueOf(Looper.myLooper()));
            /**
             * 在子线程中创建handler指定Looper.myLooper会发现为null导致程序崩溃，不指定Looper.myLooper则不会，为什么？
             */
            //mHandler1 = new Handler(Looper.myLooper());
            mHandler1 = new Handler();
//            Message msg = Message.obtain();
//            msg.arg1 = 1;
//            msg.arg2 = 2;
//            Bundle bundle = new Bundle();
//            bundle.putChar("key", 'v');
//            bundle.putString("key", "value");
//            msg.setData(bundle);
//            mHandler0.sendMessage(msg);
            Log.d("looper", String.valueOf(Looper.myLooper()));
        }).start();
    }

    /**
     * handlerThread使用
     */
    private void handlerThread() {
        // 步骤1：创建HandlerThread实例对象
        // 传入参数 = 线程名字，作用 = 标记该线程
        HandlerThread mHandlerThread = new HandlerThread("handlerThread");
        // 步骤2：启动线程
        mHandlerThread.start();
        // 步骤3：创建工作线程Handler & 复写handleMessage（）
        // 作用：关联HandlerThread的Looper对象、实现消息处理操作 & 与 其他线程进行通信
        // 注：消息处理操作（HandlerMessage（））的执行线程 = mHandlerThread所创建的工作线程中执行
        Handler workHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        };
        // 步骤4：使用工作线程Handler向工作线程的消息队列发送消息
        // 在工作线程中，当消息循环时取出对应消息 & 在工作线程执行相关操作
        // a. 定义要发送的消息
        Message msg = Message.obtain();
        //消息的标识
        msg.what = 1;
        // b. 通过Handler发送消息到其绑定的消息队列
        workHandler.sendMessage(msg);
        // 步骤5：结束线程，即停止线程的消息循环
        mHandlerThread.quit();

    }
    /**
     * 最佳创建Message
     * 创建 Message 实例的最佳方式 由于 Handler 极为常用，所以为了节省开销，Android 给 Message 设计了回收 机制，所以我们在使用的时候尽量复用 Message ，减少内存消耗。
     * 方法有二：
     * 1. 通过 Message 的静态方法 Message.obtain(); 获取；
     * 2. 通过 Handler 的公有方法 handler.obtainMessage(); 。
     */
    /**
     * 子线程更新UI
     */
    private void updateUIInThread() {
        Button button = findViewById(R.id.test);
        //1.view的post
        button.post(new Runnable() {
            @Override
            public void run() {
                button.setText("测试update");
            }
        });
        //2.handler的post
        mHandler0.post(new Runnable() {
            @Override
            public void run() {
                button.setText("测试update");
            }
        });
        //activity的runOnUIThread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.setText("测试update");
            }
        });
    }
    /**
     * 子线程中弹Toast错误使用方法，dialog同理
     */
    private void toastInThreadError(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(HandlerJavaActivity.this,"子线程提示",Toast.LENGTH_SHORT);
            }
        }).start();
    }
    /**
     * 子线程中弹Toast正确使用方法，dialog同理
     */
    private void toastInThreadCorrect(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(HandlerJavaActivity.this,"子线程提示",Toast.LENGTH_SHORT);
                Looper.loop();
            }
        }).start();
    }
}
