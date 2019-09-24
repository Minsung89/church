package com.ydn.church;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydn.church.Adapter.ResponsiveReadingAdapter;
import com.ydn.church.Database.ResponsiveReadingHelper;
import com.ydn.church.Entity.ResponsiveReading;

import java.util.ArrayList;
import java.util.List;

public class ResponsiveReadingActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView rrBackBtn, rrHomeBtn;
    RecyclerView rrList;

    ResponsiveReadingHelper rrh;
    String DATABASE_NAME = "CHURCH";

    ResponsiveReadingAdapter rra;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.responsive_reading);

        rrBackBtn = (ImageView) findViewById(R.id.rr_back_btn);
        rrHomeBtn = (ImageView) findViewById(R.id.rr_home_btn);
        rrList = (RecyclerView) findViewById(R.id.rr_rv);
        rrList.setHasFixedSize(true);

        rrh = new ResponsiveReadingHelper(this,DATABASE_NAME);
        List<ResponsiveReading> rrs = rrh.getData();
        rra = new ResponsiveReadingAdapter(rrs,this);
        rrList.setLayoutManager(new LinearLayoutManager(this));

        rrList.setAdapter(rra);

        rrBackBtn.setOnClickListener(this::onClick);
        rrHomeBtn.setOnClickListener(this::onClick);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rr_back_btn :
                onBackPressed();
                break;
            case R.id.rr_home_btn :
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
        }
    }
}
