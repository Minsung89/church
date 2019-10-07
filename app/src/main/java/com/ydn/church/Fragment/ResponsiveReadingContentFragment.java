package com.ydn.church.Fragment;
import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ydn.church.Entity.ResponsiveReading;
import com.ydn.church.R;



public class ResponsiveReadingContentFragment extends Fragment {

    ResponsiveReading rr;
    public LinearLayout linearLayout;
    public ScrollView scrollView;
    public ResponsiveReadingContentFragment(){}


    @SuppressLint("ValidFragment")
    public ResponsiveReadingContentFragment(ResponsiveReading rr){
        this.rr = rr;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.responsive_reading_content_fragment, container, false);

        scrollView = (ScrollView) rootview.findViewById(R.id.rr_content_fm_sc);
        linearLayout = (LinearLayout) rootview.findViewById(R.id.rr_content_fm);


        //그러고 출력
        String content = rr.getContents();
        int count = 0;
        while (true) {
            //사회
            count = content.indexOf("회중");
            textview(content.substring(0,count));
            content = content.substring(count);
            //회중
            count = content.indexOf("사회");
            if(count != -1) {
                textview(content.substring(0, count));
                content = content.substring(count);
            }
            else{
                count = content.indexOf("(다같이)");
                if(count != -1) {
                    textview(content.substring(0, count));
                    textview(content.substring(count));
                }
                break;
            }


        }


        return rootview;
    }
    public void textview(String content){
        TextView tv = new TextView(getActivity());
        tv.setText(content);
        tv.setTextSize(20);

        if(content.contains("사회") || content.contains("다같이")){
            tv.setTypeface(Typeface.DEFAULT_BOLD);
            tv.setTextColor(ContextCompat.getColor(getActivity(),R.color.colorBlack));
        }
        //layout_width, layout_height, gravity 설정
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        tv.setLayoutParams(lp);
        tv.setPadding(20,20,20,20);
        linearLayout.addView(tv);
    }


}
