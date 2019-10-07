package com.ydn.church;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ydn.church.Adapter.ContemplationAdapter;
import com.ydn.church.Database.MyAlarmHelper;
import com.ydn.church.Entity.MyAlarm;

import java.util.List;

public class ContemplationActivity extends AppCompatActivity implements View.OnClickListener {
    
    ImageView contemplationBackBtn, contemplationHomeBtn;
    RecyclerView contemplationRv;
    
    String DATABASE_NAME = "CHURCH";
    MyAlarmHelper mah;

    List<MyAlarm> contemplations;
    ContemplationAdapter contemplationAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contemplation);

        contemplationBackBtn = (ImageView) findViewById(R.id.contemplation_back_btn);
        contemplationHomeBtn = (ImageView) findViewById(R.id.contemplation_home_btn);
        contemplationRv = (RecyclerView) findViewById(R.id.contemplation_rv);

        contemplationRv.setHasFixedSize(true);

        mah = new MyAlarmHelper (this,DATABASE_NAME);
        contemplations = mah.getData();
        if(contemplations != null) {
            contemplationAdapter = new ContemplationAdapter(contemplations, this);
            contemplationRv.setLayoutManager(new LinearLayoutManager(this));

            contemplationRv.setAdapter(contemplationAdapter);

            //밀어서 삭제하기
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(contemplationRv);
        }


        contemplationBackBtn.setOnClickListener(this::onClick);
        contemplationHomeBtn.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.contemplation_back_btn:
                onBackPressed();
                break;
            case R.id.contemplation_home_btn:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                break;


        }
    }

    //밀어서 삭제
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Log.i("ContemplationActivity", "onMove");
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            // 삭제되는 아이템의 포지션을 가져온다
            final int position = viewHolder.getAdapterPosition();
            // 데이터의 해당 포지션을 삭제한다
            contemplations.remove(position);
            // 아답타에게 알린다
            contemplationRv.getAdapter().notifyItemRemoved(position);

        }
    };
}
