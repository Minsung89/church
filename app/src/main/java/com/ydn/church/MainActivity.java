package com.ydn.church;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ydn.church.Database.ChurchDB;
import com.ydn.church.Database.MalsseumHelper;
import com.ydn.church.Dialog.ProgressDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Database
    String DATABASE_NAME = "CHURCH";
    ChurchDB churchDB;
    //DataabaseHelper
    MalsseumHelper mh;

    Button mainMalsseumBtn, mainMyMalsseumBtn, mainHymnBtn, mainContemplationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB
        churchDB = new ChurchDB(getApplicationContext());
        mh = new MalsseumHelper(getApplicationContext(),DATABASE_NAME);

        //데이터 넣기
        ProgressDialog pd = new ProgressDialog(getApplicationContext());
        pd.setMessage("데이터 인서트중");
        pd.show();
        mh.insert();
        pd.dismiss();

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
