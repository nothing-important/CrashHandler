package com.example.administrator.androidcrashhandler;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static volatile CrashHandler crashHandler;

    private Context context;

    private CrashHandler(){}

    public void init(Context context){
        this.context = context;
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
    public void uncaughtException(Thread t, final Throwable e) {
        // 提示信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Intent intent = new Intent(context , CrashActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("exceptionOfCrash" , e.getMessage());
                context.startActivity(intent);
                Looper.loop();
            }
        }.start();
        ActivityCollector.finishAll();
        Process.killProcess(Process.myPid());
        System.exit(1);
    }
}
