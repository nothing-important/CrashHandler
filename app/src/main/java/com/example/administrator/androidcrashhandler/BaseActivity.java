package com.example.administrator.androidcrashhandler;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class BaseActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
