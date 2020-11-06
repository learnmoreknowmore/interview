package com.decay.interview.crash;

/**
 * @author NDK异常捕获
 * linux信号机制：
 * 信息机制是linux进程通信的一种重要方式，linux一方面用于正常的进程间通信和同步，另一方面用于监控系统异常以及中断。
 * 当应用程序运行异常时，linux内核将产生错误信号并通知当前进程，当前进程接收到该错误信号，有三种处理方式：
 * 1.忽略该信号
 * 2.捕捉该信号并执行对应的信号处理函数(信号处理程序)
 * 3.执行该信号的缺省操作(比如中断进程)
 * <p>
 * 常见的崩溃信号列表：名称->含义
 * SIGSEGV 内存引用无效
 * SIGBUS 访问内存对象未定义部分
 * SIGFPE 算数运算错误，例如除以0
 * SIGILL 非法指令。例如执行垃圾或者特权指令
 * SIGSYS 糟糕的系统调用
 * SIGXCPU 超过cpu时间限制
 * SIGXFSZ 文件大小限制
 * 一般出现崩溃信号，android系统默认缺省操作就是退出该进程，但是系统允许我们给某个进程的某个特定信号注册一个对应的处理函数(Signal),
 * 即对该信号的默认操作进行修改，因此NDK CRASH的监控可以采用这种信号机制，捕捉崩溃信号执行我们自己的信号函数从而捕捉NDK CRASH
 * <p>
 * tombStones(墓碑)
 * 普通应用无权限读取墓碑文件
 * 墓碑文件位于/data/tombStones/下解析墓碑问价以及breakPad都可以使用addr2line工具
 * android 本机程序本质就是一个linux程序，当执行时发生严重错误时也会导致程序崩溃，然后产生一个记录崩溃的现场信息文件,而这个文件在android系统中就是tombStones文件
 */
public class NDKCrashHandler {
}
