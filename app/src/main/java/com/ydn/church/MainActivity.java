package com.ydn.church;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button mainMalsseumBtn, mainMyMalsseumBtn, mainHymnBtn, mainContemplationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainMalsseumBtn = findViewById(R.id.main_malsseum_btn);
        mainMyMalsseumBtn = findViewById(R.id.main_my_malsseum_btn);
        mainHymnBtn = findViewById(R.id.main_hymn_btn);
        mainContemplationBtn = findViewById(R.id.main_contemplation_btn);

        mainContemplationBtn.setOnClickListener((v)-> Log.i("로그찍히나?","로그찍혀"));
        mainMalsseumBtn.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){

            case R.id.main_malsseum_btn :
                intent = new Intent(this, MalsseumActivity.class);
                startActivity(intent);
                break;
            case R.id.main_my_malsseum_btn :
                break;
            case R.id.main_hymn_btn :
                break;
            case R.id.main_contemplation_btn :
                break;

        }
    }
}
