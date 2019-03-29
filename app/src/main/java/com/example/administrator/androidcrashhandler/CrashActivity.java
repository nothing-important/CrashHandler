package com.example.administrator.androidcrashhandler;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Process;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.TextView;

import java.util.Locale;

public class CrashActivity extends BaseActivity {

    private TextView prompt , crashMessage;
    private String exceptionOfCrash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        initIntent();
        initView();
        countDownTimer.start();
    }

    private void initIntent() {
        Intent intent = getIntent();
        if (intent == null)return;
        exceptionOfCrash = intent.getStringExtra("exceptionOfCrash");
    }

    private void initView() {
        prompt = findViewById(R.id.prompt);
        crashMessage = findViewById(R.id.crashMessage);
        crashMessage.setText(exceptionOfCrash);
    }

    private CountDownTimer countDownTimer = new CountDownTimer(10000 , 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            prompt.setText(String.format(Locale.getDefault() , "Warning!\nnuclear missile will be launched in %1s second" , millisUntilFinished/1000));
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
