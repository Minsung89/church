package com.ydn.church;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydn.church.Adapter.ResponsiveReadingPagerAdapter;
import com.ydn.church.Database.ResponsiveReadingHelper;
import com.ydn.church.Entity.ResponsiveReading;

import java.util.List;

public class ResponsiveReadingContentActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager rrContentVp;
    TextView rrContentPage;
    ImageView rrContentPageNext, rrContentPageBack, rrContentPageSearch,
            rrBackBtn, rrContentHomeBtn, rrContentEnlargement, rrContentReduction;

    ResponsiveReadingHelper rrh;
    String DATABASE_NAME = "CHURCH";

    ResponsiveReadingPagerAdapter responsiveReadingPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.responsive_reading_content);

        Intent getIntent = getIntent();
        //페이지 번호
        ResponsiveReading rr = (ResponsiveReading)getIntent.getSerializableExtra("rr");

        rrh = new ResponsiveReadingHelper(this,DATABASE_NAME);

        rrContentVp = (ViewPager) findViewById(R.id.rr_content_vp);
        rrContentPage = (TextView) findViewById(R.id.rr_content_page);
        rrContentPageNext = (ImageView) findViewById(R.id.rr_content_page_next);
        rrContentPageBack = (ImageView) findViewById(R.id.rr_content_page_back);
        rrBackBtn = (ImageView) findViewById(R.id.rr_content_back_btn);
        rrContentHomeBtn = (ImageView) findViewById(R.id.rr_content_home_btn);

        rrContentPageNext.setOnClickListener(this);
        rrContentPageBack.setOnClickListener(this);
        rrBackBtn.setOnClickListener(this);
        rrContentHomeBtn.setOnClickListener(this);


        List<ResponsiveReading> rrs = rrh.getData();
        //page 이름
        rrContentPage.setText(rr.getTitle());
        //첫 페이지 다음&뒤로가기 없애기
        if(rr.getSequence().equals("1"))
            rrContentPageBack.setVisibility(View.INVISIBLE);
        if(rr.getSequence().equals(String.valueOf(rrs.size())))
            rrContentPageNext.setVisibility(View.INVISIBLE);
        //페이지뷰 어댑터
        responsiveReadingPagerAdapter = new ResponsiveReadingPagerAdapter(getSupportFragmentManager(),rrs);
        rrContentVp.setAdapter(responsiveReadingPagerAdapter);
        rrContentVp.setCurrentItem(Integer.valueOf(rr.getSequence()) - 1);

        //페이지뷰 리스너
        rrContentVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int i) {
            //화면 number
                rrContentPage.setText(rrs.get(i).getTitle());
                if(i == 0) {
                    rrContentPageBack.setVisibility(View.INVISIBLE);
                    rrContentPageNext.setVisibility(View.VISIBLE);
                }else if(i == rrs.size()-1) {
                    rrContentPageNext.setVisibility(View.INVISIBLE);
                    rrContentPageBack.setVisibility(View.VISIBLE);
                }else{
                    rrContentPageNext.setVisibility(View.VISIBLE);
                    rrContentPageBack.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.rr_content_page_search :
//                if(searchFalg){
//                    searchFalg = false;
//                    rrContentPageSelect.startAnimation(amIn);
//                }
//                else {
//                    searchFalg = true;
//                    rrContentPageSelect.startAnimation(amOut);
//                }
//                break;
            case R.id.rr_content_page_next :
                rrContentVp.setCurrentItem(rrContentVp.getCurrentItem()+1);
                break;
            case R.id.rr_content_page_back :
                rrContentVp.setCurrentItem(rrContentVp.getCurrentItem()-1);
                break;
            case R.id.rr_content_back_btn :
                onBackPressed();
                break;
            case R.id.rr_content_home_btn :
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
        }
    }
}
