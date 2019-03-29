package com.example.administrator.androidcrashhandler;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.widget.Toast;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static volatile CrashHandler crashHandler;

    private Context context;
    private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    private CrashHandler(){}

    public void init(Context context){
        this.context = context;
        defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static CrashHandler getCrashHander(){
        if (crashHandler == null){
            synchronized (CrashHandler.class){
                if (crashHandler == null){
                    crashHandler = new CrashHandler();
                }
            }
        }
        return crashHandler;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // 提示信息
        Intent intent = new Intent(context, CrashActivity.class);
        context.startActivity(intent);
        ActivityCollector.finishAll();
        Process.killProcess(Process.myPid());
        System.exit(1);
    }
}
