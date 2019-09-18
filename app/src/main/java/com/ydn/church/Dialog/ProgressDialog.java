package com.ydn.church.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ydn.church.R;


public class ProgressDialog extends Dialog {

    String message;
    int img;

    public ProgressBar progressBar;
    private TextView textView;


    public ProgressDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //타이틀 바 삭제
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT)); //다이얼로그 백그라운드 제거

        setContentView(R.layout.progress_circle_dialog);
        progressBar = (ProgressBar) findViewById(R.id.progress_circle_dialog);
        textView= (TextView) findViewById(R.id.progress_circle_dialog_txt);

        textView.setText(message);

    }


    public void setMessage(String message){
        this.message = message;
    }
}
