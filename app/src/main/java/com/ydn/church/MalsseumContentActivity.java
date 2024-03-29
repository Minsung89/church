package com.ydn.church;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ydn.church.Adapter.MalsseumPagerAdapter;
import com.ydn.church.Database.MalsseumHelper;
import com.ydn.church.Database.MyAlarmHelper;
import com.ydn.church.Fragment.MalsseumContentFragment;
import com.ydn.church.Util.AlarmTime;
import com.ydn.church.Util.AlarmUtil;

import java.util.Calendar;
import java.util.List;

public class MalsseumContentActivity extends AppCompatActivity implements View.OnClickListener{

    MalsseumHelper mh;
    MyAlarmHelper mah;

    String DATABASE_NAME = "CHURCH";

    ViewPager malsseumContentVp;
    TextView malsseumContentTitle, malsseumContentPage;
    HorizontalScrollView malsseumContentPageSelect;
    LinearLayout malsseumContentPageSelectLl;
    ImageView malsseumContentPageNext, malsseumContentPageBack, malsseumContentPageSearch,
            malsseumBackBtn, malsseumContentHomeBtn, malsseumContentEnlargement, malsseumContentReduction,
            malsseumContentAlarm;

    MalsseumPagerAdapter malsseumPagerAdapter;

    //애니메이션
    Animation amIn;
    Animation amOut;

    int pageCount = 0;
    //search
    Boolean searchFalg = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.malsseum_content);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        int page = intent.getIntExtra("page",1);



        malsseumContentTitle = (TextView) findViewById(R.id.malsseum_content_title);
        malsseumContentVp = (ViewPager) findViewById(R.id.malsseum_content_vp);
        malsseumContentPage = (TextView) findViewById(R.id.malsseum_content_page);
        malsseumContentPageSelect = (HorizontalScrollView) findViewById(R.id.malsseum_content_page_select);
        malsseumContentPageSelectLl = (LinearLayout) findViewById(R.id.malsseum_content_page_select_ll);
        malsseumContentPageSearch = (ImageView) findViewById(R.id.malsseum_content_page_search);
        malsseumContentPageNext = (ImageView) findViewById(R.id.malsseum_content_page_next);
        malsseumContentPageBack = (ImageView) findViewById(R.id.malsseum_content_page_back);
        malsseumBackBtn = (ImageView) findViewById(R.id.malsseum_content_back_btn);
        malsseumContentHomeBtn = (ImageView) findViewById(R.id.malsseum_content_home_btn);
//        malsseumContentEnlargement = (ImageView) findViewById(R.id.malsseum_content_enlargement);
//        malsseumContentReduction = (ImageView) findViewById(R.id.malsseum_content_reduction);

        malsseumContentAlarm = (ImageView) findViewById(R.id.malsseum_content_alarm);

        //리스너
        malsseumContentPageSearch.setOnClickListener(this);
        malsseumContentPageNext.setOnClickListener(this);
        malsseumContentPageBack.setOnClickListener(this);
        malsseumBackBtn.setOnClickListener(this);
        malsseumContentHomeBtn.setOnClickListener(this);
        malsseumContentAlarm.setOnClickListener(this);
        //디비
        mh = new MalsseumHelper(this,DATABASE_NAME);
        mah = new MyAlarmHelper(this,DATABASE_NAME);
        pageCount = mh.getPageCount(title);

        Log.i("pageeeeeee", page+"");
        Log.i("pageeeeeee", pageCount+"");
        //서치 페이지 생성
        textview(pageCount);

        //나타나는 && 사라지는 애니메이션
        amIn = AnimationUtils.loadAnimation(getApplication(),R.anim.malsseum_content_text_fade_in);
        amOut = AnimationUtils.loadAnimation(getApplication(),R.anim.malsseum_content_text_fade_out);
        amIn.setFillAfter(true);
        amOut.setFillAfter(true);
        amIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("malsseum_content_text_fade_in","onAnimationStart");

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("malsseum_content_text_fade_in","onAnimationEnd");
                malsseumContentPageSelect.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("malsseum_content_text_fade_in","onAnimationRepeat");
            }
        });
        amOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("malsseum_content_text_fade_out","onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("malsseum_content_text_fade_out","onAnimationEnd");
                malsseumContentPageSelect.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("malsseum_content_text_fade_out","onAnimationRepeat");
            }
        });

        //말씀 타이틀
        malsseumContentTitle.setText(title);
        //페이지뷰
        malsseumPagerAdapter = new MalsseumPagerAdapter(getSupportFragmentManager(),pageCount,mh,title,malsseumContentEnlargement,malsseumContentReduction);
        malsseumContentVp.setAdapter(malsseumPagerAdapter);

        if(page != 0) {
            malsseumContentVp.setCurrentItem(page - 1);
            malsseumContentPage.setText(page + "장");
        }
        else
            malsseumContentVp.setCurrentItem(0);

        if(malsseumContentVp.getCurrentItem() == 0)
            malsseumContentPageBack.setVisibility(View.INVISIBLE);
        if(pageCount -1 == malsseumContentVp.getCurrentItem())
            malsseumContentPageNext.setVisibility(View.INVISIBLE);

        //페이지뷰 리스너
        malsseumContentVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                //옆으로 밀고 있을 때 화면 마지막은 보여지는 화면
            }
            @Override
            public void onPageSelected(int i) {
                //화면 number
                malsseumContentPage.setText(i+1+"장");
                if(i == 0) {
                    malsseumContentPageBack.setVisibility(View.INVISIBLE);
                    malsseumContentPageNext.setVisibility(View.VISIBLE);
                }else if(i == pageCount-1) {
                    malsseumContentPageNext.setVisibility(View.INVISIBLE);
                    malsseumContentPageBack.setVisibility(View.VISIBLE);
                }else{
                    malsseumContentPageNext.setVisibility(View.VISIBLE);
                    malsseumContentPageBack.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                // i = 1은 손가락으로 옆으로 밀고 있을 때
                // i = 2는 손가락을 뗏을 때
                // i = 0은 화면이 멈췄을 때

            }
        });
    }

    //페이지 생성
    public void textview(int page){
        //TextView 생성
        for (int i = 0 ; i < page ; i++) {
            TextView view1 = new TextView(this);
            view1.setText(i+1 + "장");
            view1.setTextSize(25);

            //layout_width, layout_height, gravity 설정
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            view1.setLayoutParams(lp);
            view1.setPadding(20,20,20,20);
            int number = i;
            view1.setOnClickListener(view -> malsseumContentVp.setCurrentItem(number));
            //부모 뷰에 추가
            malsseumContentPageSelectLl.addView(view1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.malsseum_content_page_search :
                if(searchFalg){
                    searchFalg = false;
                    malsseumContentPageSelect.startAnimation(amIn);
                }
                else {
                    searchFalg = true;
                    malsseumContentPageSelect.startAnimation(amOut);
                }
                break;
            case R.id.malsseum_content_page_next :
                malsseumContentVp.setCurrentItem(malsseumContentVp.getCurrentItem()+1);
                break;
            case R.id.malsseum_content_page_back :
                malsseumContentVp.setCurrentItem(malsseumContentVp.getCurrentItem()-1);
                break;
            case R.id.malsseum_content_back_btn :
                onBackPressed();
                break;
            case R.id.malsseum_content_home_btn :
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;
            case R.id.malsseum_content_alarm :
                //알람 설정
                //timepicker
                TimePickerDialog tpd = new TimePickerDialog(MalsseumContentActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth, (view, hourOfDay, minute) ->{
                    Toast.makeText(getApplicationContext(),"묵상말씀시간(매일 "+hourOfDay + ":" + minute+")",Toast.LENGTH_LONG).show();
                    //알림 및 저장
                    int sequence = Integer.valueOf( mah.getSequence());
                    //알림 시간 설정
                    Calendar calendar = new AlarmTime().setAlarmTime(hourOfDay,minute);
                    AlarmUtil alarmUtil = new AlarmUtil();
                    alarmUtil.setAlarm(this,sequence,malsseumContentTitle.getText().toString(), malsseumContentVp.getCurrentItem()+1,calendar);
                    mah.insert(String.valueOf(sequence + 1),malsseumContentTitle.getText().toString(),malsseumContentPage.getText().toString(),hourOfDay + ":" + minute,"Y");

                },Calendar.HOUR_OF_DAY,Calendar.MINUTE,false);

                tpd.setTitle(malsseumContentTitle.getText().toString()+" "+malsseumContentPage.getText().toString());
                tpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                tpd.show();
                break;
        }
    }


}
