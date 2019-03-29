package com.example.administrator.androidcrashhandler;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Process;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;

import java.util.Locale;

public class CrashActivity extends BaseActivity {

    private TextView prompt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        initView();
        countDownTimer.start();
    }

    private void initView() {
        prompt = findViewById(R.id.prompt);
    }

    private CountDownTimer countDownTimer = new CountDownTimer(6000 , 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            prompt.setText(String.format(Locale.getDefault() , "程序出现错误，将在%1s秒内关闭" , millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            ActivityCollector.finishAll();
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
