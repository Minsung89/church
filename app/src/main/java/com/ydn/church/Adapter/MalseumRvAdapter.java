package com.ydn.church.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydn.church.Entity.MalsseumContent;
import com.ydn.church.R;

import java.util.List;

public class MalseumRvAdapter extends RecyclerView.Adapter<MalseumRvAdapter.ItemViewHolder> {

    private List<MalsseumContent> mcs;
    private Context context;
    ImageView malsseumContentEnlargement, malsseumContentReduction;
    public float pageTextSize = 60;


    public MalseumRvAdapter(List<MalsseumContent> mcs, Context context, ImageView malsseumContentEnlargement, ImageView malsseumContentReduction){
        this.mcs = mcs;
        this.context = context;
        this.malsseumContentReduction = malsseumContentReduction;
        this.malsseumContentEnlargement = malsseumContentEnlargement;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Log.d("onCreateViewHolder","========onCreateViewHolder========");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.malsseum_content_rv_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int i) {
        Log.d("onBindViewHolder","========onBindViewHolder========");
        Log.i("pageTextSize",pageTextSize+"");
        holder.malsseumContentFmRvTitle.setText(mcs.get(i).getSection());
        holder.malsseumContentFmRvContent.setText(mcs.get(i).getContents());
        holder.malsseumContentFmRvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, pageTextSize);
        holder.malsseumContentFmRvContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, pageTextSize);


    }


    @Override
    public int getItemCount() {
        if(mcs != null)
            return mcs.size();
        else
            return 0;
    }

    public void enlargement(){

    }
    public void reduction(){
        ItemViewHolder.class.getMethods();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder{


        TextView malsseumContentFmRvTitle, malsseumContentFmRvContent;

        public ItemViewHolder(@NonNull View itemView) {

            super(itemView);

            malsseumContentFmRvTitle = (TextView) itemView.findViewById(R.id.malsseum_content_fm_rv_title);
            malsseumContentFmRvContent = (TextView) itemView.findViewById(R.id.malsseum_content_fm_rv_content);

//            malsseumContentEnlargement.setOnClickListener(view -> {
//                if(pageTextSize < 90)
//                    pageTextSize = malsseumContentFmRvTitle.getTextSize()+10;
//                notifyDataSetChanged();
//
//            });
//            malsseumContentReduction.setOnClickListener(view -> {
//                if(pageTextSize > 40)
//                    pageTextSize = malsseumContentFmRvTitle.getTextSize()-10;
//                notifyDataSetChanged();
//
//            });

        }

        public void test(){

        }
        public void test3(){

        }
//        public void enlargement(){
//            malsseumContentFmRvTitle.setTextSize(malsseumContentFmRvTitle.getTextSize()+1);
//            malsseumContentFmRvContent.setTextSize(malsseumContentFmRvContent.getTextSize()+1);
//        }
//        public void reduction(){
//            malsseumContentFmRvTitle.setTextSize(malsseumContentFmRvTitle.getTextSize()-1);
//            malsseumContentFmRvContent.setTextSize(malsseumContentFmRvContent.getTextSize()-1);
//        }
    }


}
