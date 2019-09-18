package com.ydn.church;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.ydn.church.Adapter.MalsseumAdapter;
import com.ydn.church.Entity.Malsseum;

import java.util.ArrayList;

public class MalsseumActivity extends AppCompatActivity {

    ImageView malsseumBackBtn, malsseumHomeBtn;
    ExpandableListView malsseumList;

    ArrayList<Malsseum> malsseums;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malsseum);

        malsseumBackBtn = findViewById(R.id.malsseum_back_btn);
        malsseumHomeBtn = findViewById(R.id.malsseum_home_btn);
        malsseumList = findViewById(R.id.malsseum_list);

        //뒤로가기
        malsseumBackBtn.setOnClickListener((v) -> onBackPressed());
        //메인
        malsseumHomeBtn.setOnClickListener((v) -> {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                });

        //대분류
        setExpandableListView();

        MalsseumAdapter ma = new MalsseumAdapter(getApplicationContext(),malsseums);
        View headerView = View.inflate(this, R.layout.malsseum_hedaer, null);
        headerView.setLayoutParams(new ExpandableListView.LayoutParams(ExpandableListView.LayoutParams.FILL_PARENT, ExpandableListView.LayoutParams.WRAP_CONTENT));
        malsseumList.addHeaderView(headerView);
        malsseumList.setAdapter(ma);

    }

    private void setExpandableListView(){
        malsseums = new ArrayList<>();



        Malsseum malsseum2 = new Malsseum();

        malsseum2.setMainCategory("구약");
        ArrayList<String> cm = new ArrayList<>();
        cm.add("01창세기");
        cm.add("02출애굽기");
        cm.add("03레위기");
        cm.add("04민수기");
        cm.add("05신명기");
        cm.add("06여호수아");
        cm.add("07사사기");
        cm.add("08룻기");
        cm.add("09사무엘-상");
        cm.add("10사무엘-하");
        cm.add("11열왕기-상");
        cm.add("12열왕기-하");
        cm.add("13역대-상");
        cm.add("14역대-하");
        cm.add("15에스라");
        cm.add("16느헤미야");
        cm.add("17에스더");
        cm.add("18욥기");
        cm.add("19시편");
        cm.add("20잠언");
        cm.add("21전도서");
        cm.add("22아가");
        cm.add("23이사야");
        cm.add("24예레미야");
        cm.add("25예레미아애가");
        cm.add("26에스겔");
        cm.add("27다니엘");
        cm.add("28호세아");
        cm.add("29요엘");
        cm.add("30아모스");
        cm.add("31오바댜");
        cm.add("32요나");
        cm.add("33마기");
        cm.add("34나훔");
        cm.add("35하박국");
        cm.add("36스바냐");
        cm.add("37학개");
        cm.add("38스가랴");
        cm.add("39말라기");

        malsseum2.setCategory(cm);
        malsseums.add(malsseum2);

        Malsseum malsseum3 = new Malsseum();
        malsseum3.setMainCategory("신약");
        ArrayList<String> cm3 = new ArrayList<>();
        cm3.add("01마태복음");
        cm3.add("02마가복음");
        cm3.add("03누가복음");
        cm3.add("04요한복음");
        cm3.add("05사도행전");
        cm3.add("06로마서");
        cm3.add("07고린도전서");
        cm3.add("08고린도후서");
        cm3.add("09갈라디아서");
        cm3.add("10에베소서");
        cm3.add("11빌립보서");
        cm3.add("12골로새서");
        cm3.add("13데살로니가전서");
        cm3.add("14데살로니가후서");
        cm3.add("15디모데전서");
        cm3.add("16디모데후서");
        cm3.add("17디도서");
        cm3.add("18빌레몬서");
        cm3.add("19히브리서");
        cm3.add("20야고보서");
        cm3.add("21베드로전서");
        cm3.add("22베드로후서");
        cm3.add("23요한이서");
        cm3.add("24요한삼서");
        cm3.add("25요한일서");
        cm3.add("26유다서");
        cm3.add("27요한계시록");
        malsseum3.setCategory(cm3);

        malsseums.add(malsseum3);

    }
}
