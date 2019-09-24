package com.ydn.church.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ydn.church.Entity.ResponsiveReading;
import com.ydn.church.R;
import com.ydn.church.ResponsiveReadingContentActivity;

import java.util.List;

public class ResponsiveReadingAdapter extends RecyclerView.Adapter<ResponsiveReadingAdapter.ItemViewHolder> {

    private List<ResponsiveReading> rrs;
    private Context context;


    public ResponsiveReadingAdapter(List<ResponsiveReading> rrs, Context context){
        this.rrs = rrs;
        this.context = context;
        Log.i("rrs",rrs.get(0).toString());
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Log.d("onCreateViewHolder","========onCreateViewHolder========");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.responsive_reading_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int i) {
        Log.d("onBindViewHolder","========onBindViewHolder========");

        holder.rRItemTextview.setText(rrs.get(i).getTitle());
        holder.mView.setOnClickListener(view -> {
            Intent intent = new Intent(context,ResponsiveReadingContentActivity.class);
            intent.putExtra("rr", rrs.get(i));
            context.startActivity(intent);
        });

    }


    @Override
    public int getItemCount() {
        if(rrs != null)
            return rrs.size();
        else
            return 0;
    }



    public static class ItemViewHolder extends RecyclerView.ViewHolder {


        TextView rRItemTextview;
        View mView;


        public ItemViewHolder(@NonNull View itemView) {

            super(itemView);

            mView = itemView;
            rRItemTextview = (TextView) itemView.findViewById(R.id.rr_item_textview);


        }
    }

}
