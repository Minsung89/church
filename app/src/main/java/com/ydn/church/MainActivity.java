package com.ydn.church;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.ydn.church.Database.ChurchDB;
import com.ydn.church.Database.MalsseumHelper;
import com.ydn.church.Dialog.ProgressDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Database
    String DATABASE_NAME = "CHURCH";
    ChurchDB churchDB;
    //DataabaseHelper
    MalsseumHelper mh;

    LinearLayout mainMalsseumBtn, mainHymnBtn, mainContemplationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);



                //DB
        churchDB = new ChurchDB(this);
        mh = new MalsseumHelper(this,DATABASE_NAME);
        churchDB.connectDatabase();
        churchDB.createTable();
        //데이터 넣기
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("데이터 인서트중");
        pd.show();
        String getDate = mh.getData();
        if(getDate == null || getDate.equals("") || getDate.equals("0"))
            mh.insert();
        pd.dismiss();

        mainMalsseumBtn = (LinearLayout)findViewById(R.id.main_malsseum_btn);
        mainHymnBtn = (LinearLayout) findViewById(R.id.main_hymn_btn);
        mainContemplationBtn = (LinearLayout) findViewById(R.id.main_contemplation_btn);

        mainContemplationBtn.setOnClickListener((v)-> Log.i("로그찍히나?","로그찍혀"));
        mainMalsseumBtn.setOnClickListener(this::onClick);
        mainHymnBtn.setOnClickListener(this::onClick);

        Stetho.initializeWithDefaults(this);
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
                break;

        }
    }
}
