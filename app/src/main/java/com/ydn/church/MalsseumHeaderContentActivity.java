package com.ydn.church;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MalsseumHeaderContentActivity extends AppCompatActivity {

    TextView malsseumHeaderContentTv,malsseumHeaderContentTitle;
    ImageView malsseumHeaderContentBackBtn, malsseumHeaderContentHomeBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malsseum_header_content);

        Intent getIntent = getIntent();
        String title = getIntent.getStringExtra("title");
        int content = getIntent.getIntExtra("content",0);
        malsseumHeaderContentTitle = (TextView)findViewById(R.id.malsseum_header_content_title);
        malsseumHeaderContentTv = (TextView)findViewById(R.id.malsseum_header_content_tv);
        malsseumHeaderContentTitle.setText(title);
        malsseumHeaderContentTv.setText(content);
        malsseumHeaderContentBackBtn = (ImageView) findViewById(R.id.malsseum_header_content_back_btn);
        malsseumHeaderContentHomeBtn = (ImageView) findViewById(R.id.malsseum_header_content_home_btn);

        malsseumHeaderContentBackBtn.setOnClickListener(view -> onBackPressed());
        malsseumHeaderContentHomeBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}
