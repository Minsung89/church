package com.ydn.church;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;


public class ScreenTransformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //상태바 & 타이틀바 없애기
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        handler.sendEmptyMessageDelayed(0,1500);
        setContentView(R.layout.screen_transform);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(ScreenTransformActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
            overridePendingTransition(R.anim.load_fade_in,R.anim.load_fade_out);
        }
    };

}
