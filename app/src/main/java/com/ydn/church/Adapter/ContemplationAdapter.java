package com.ydn.church.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.ydn.church.Entity.MyAlarm;
import com.ydn.church.Entity.ResponsiveReading;
import com.ydn.church.R;
import com.ydn.church.ResponsiveReadingContentActivity;

import java.util.List;

public class ContemplationAdapter extends RecyclerView.Adapter<ContemplationAdapter.ItemViewHolder> {

    private List<MyAlarm> myAlarms;
    private Context context;


    public ContemplationAdapter(List<MyAlarm> myAlarms, Context context){
        this.myAlarms = myAlarms;
        this.context = context;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Log.d("onCreateViewHolder","========onCreateViewHolder========");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contemplation_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int i) {
        Log.d("onBindViewHolder","========onBindViewHolder========");

        holder.contemplationItemText.setText(myAlarms.get(i).titelString());
        holder.contemplationItemTime.setText(myAlarms.get(i).getAlarmTime());
        if(myAlarms.get(i).getAlarmYn().equals("Y"))
            holder.contemplationItemSwitch.setChecked(true);
        else
            holder.contemplationItemSwitch.setChecked(false);
    }


    @Override
    public int getItemCount() {
        if(myAlarms != null)
            return myAlarms.size();
        else
            return 0;
    }



    public static class ItemViewHolder extends RecyclerView.ViewHolder {


        TextView contemplationItemText, contemplationItemTime;
        Switch contemplationItemSwitch;
        View mView;

        public ItemViewHolder(@NonNull View itemView) {

            super(itemView);

            mView = itemView;

            contemplationItemText = (TextView)itemView.findViewById(R.id.contemplation_item_text);
            contemplationItemTime = (TextView)itemView.findViewById(R.id.contemplation_item_time);
            contemplationItemSwitch = (Switch)itemView.findViewById(R.id.contemplation_item_switch);
        }
    }

}
