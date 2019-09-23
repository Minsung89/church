package com.ydn.church;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ydn.church.Adapter.MalsseumAdapter;
import com.ydn.church.Database.MalsseumHelper;
import com.ydn.church.Entity.Malsseum;

import java.util.ArrayList;

public class MalsseumActivity extends AppCompatActivity {

    ImageView malsseumBackBtn, malsseumHomeBtn;
    ExpandableListView malsseumList;

    ArrayList<Malsseum> malsseums;

    MalsseumHelper mh;
    String DATABASE_NAME = "CHURCH";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malsseum);


        mh = new MalsseumHelper(this,DATABASE_NAME);

        malsseumBackBtn = (ImageView) findViewById(R.id.malsseum_back_btn);
        malsseumHomeBtn = (ImageView) findViewById(R.id.malsseum_home_btn);
        malsseumList = (ExpandableListView) findViewById(R.id.malsseum_list);

        //뒤로가기
        malsseumBackBtn.setOnClickListener((v) -> onBackPressed());
        //메인
        malsseumHomeBtn.setOnClickListener((v) -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                });

        //대분류
        setExpandableListView();

        MalsseumAdapter ma = new MalsseumAdapter(getApplicationContext(),malsseums);
        View headerView = View.inflate(this, R.layout.malsseum_hedaer, null);
        headerView.setLayoutParams(new ExpandableListView.LayoutParams(ExpandableListView.LayoutParams.MATCH_PARENT, ExpandableListView.LayoutParams.WRAP_CONTENT));
        malsseumList.addHeaderView(headerView);

        TextView malsseumApostleCreed = (TextView) headerView.findViewById(R.id.malsseum_apostle_creed);
        TextView malsseumTheLordsPrayer = (TextView)headerView.findViewById(R.id.malsseum_the_lords_prayer);

        malsseumApostleCreed.setOnClickListener(view -> {
            Intent intent = new Intent(MalsseumActivity.this,MalsseumHeaderContentActivity.class);
            intent.putExtra("title",malsseumApostleCreed.getText());
            int content =  R.string.malsseum_apostle_creed_content;
            intent.putExtra("content",content);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
        malsseumTheLordsPrayer.setOnClickListener(view -> {
            Intent intent = new Intent(MalsseumActivity.this,MalsseumHeaderContentActivity.class);
            intent.putExtra("title",malsseumTheLordsPrayer.getText());
            intent.putExtra("content",R.string.malsseum_the_lords_prayer_content);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        malsseumList.setAdapter(ma);

    }

    private void setExpandableListView(){
        malsseums = new ArrayList<>();

        Malsseum malsseum2 = new Malsseum();

        malsseum2.setMainCategory("구약");
        ArrayList<String> cm = mh.getSubTitle("구약");

        malsseum2.setCategory(cm);
        malsseums.add(malsseum2);

        Malsseum malsseum3 = new Malsseum();
        malsseum3.setMainCategory("신약");
        ArrayList<String> cm3 = mh.getSubTitle("신약");
        malsseum3.setCategory(cm3);

        malsseums.add(malsseum3);



    }
}
