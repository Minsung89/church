package com.ydn.church;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.stetho.Stetho;
import com.ydn.church.Database.ChurchDB;
import com.ydn.church.Database.MalsseumHelper;
import com.ydn.church.Database.ResponsiveReadingHelper;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Database
    String DATABASE_NAME = "CHURCH";
    ChurchDB churchDB;
    //DataabaseHelper
    MalsseumHelper mh;
    ResponsiveReadingHelper rrh;

    LinearLayout mainMalsseumBtn, mainHymnBtn, mainContemplationBtn, mainResponsiveReading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //DB
        churchDB = new ChurchDB(this);
        mh = new MalsseumHelper(this,DATABASE_NAME);
        rrh = new ResponsiveReadingHelper(this,DATABASE_NAME);

        churchDB.connectDatabase();
        churchDB.createTable();

        //데이터 넣기
        String mGetDate = mh.getData();
        String rrGetDate = rrh.getCount();
        if(mGetDate == null || mGetDate.equals("") || mGetDate.equals("0"))
            mh.insert();
        if(rrGetDate == null || rrGetDate.equals("") || rrGetDate.equals("0"))
            rrh.insert();

        mainMalsseumBtn = (LinearLayout)findViewById(R.id.main_malsseum_btn);
        mainHymnBtn = (LinearLayout) findViewById(R.id.main_hymn_btn);
        mainContemplationBtn = (LinearLayout) findViewById(R.id.main_contemplation_btn);
        mainResponsiveReading = (LinearLayout) findViewById(R.id.main_responsive_reading_btn);


        mainContemplationBtn.setOnClickListener(this::onClick);
        mainMalsseumBtn.setOnClickListener(this::onClick);
        mainHymnBtn.setOnClickListener(this::onClick);
        mainResponsiveReading.setOnClickListener(this::onClick);

        Stetho.initializeWithDefaults(this);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,2020);
        Log.i("현재 날짜",cal.get(Calendar.YEAR)+"년 " + cal.get(Calendar.MONTH) +"월 ");
        Log.i("현재는",cal+"");

    }



    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){

            case R.id.main_malsseum_btn :
                intent = new Intent(this, MalsseumActivity.class);
                startActivity(intent);
                break;
            case R.id.main_hymn_btn :
                mh.getData();
                break;
            case R.id.main_contemplation_btn :
                intent = new Intent(this, ContemplationActivity.class);
                startActivity(intent);
                break;
            case R.id.main_responsive_reading_btn :
                intent = new Intent(this, ResponsiveReadingActivity.class);
                startActivity(intent);
                break;

        }
    }
}
