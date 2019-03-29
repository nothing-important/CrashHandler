package com.example.administrator.androidcrashhandler;

import android.app.Application;

public class ProgramApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getCrashHander();
        crashHandler.init(this);
    }
}
