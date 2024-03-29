package com.ydn.church.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ydn.church.Entity.Malsseum;
import com.ydn.church.MalsseumContentActivity;
import com.ydn.church.R;

import java.util.ArrayList;

public class MalsseumAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Malsseum> malsseums;
    private LayoutInflater layoutInflater;


    public MalsseumAdapter(Context context, ArrayList<Malsseum> malsseums){
        this.malsseums = malsseums;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override //부모 크기
    public int getGroupCount() {
        return malsseums.size();
    }

    @Override //자식 크기
    public int getChildrenCount(int i) {
        return malsseums.get(i).getCategory().size();
    }

    @Override //부모 문자
    public Object getGroup(int i) {
        return malsseums.get(i).getMainCategory();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return malsseums.get(groupPosition).getCategory().get(childPosition);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if(view == null)
            view = layoutInflater.inflate(R.layout.malsseum_item,null);

        TextView tv = (TextView) view.findViewById(R.id.malsseum_item_textview);
        ImageView iv= (ImageView) view.findViewById(R.id.malsseum_item_image_view);
        iv.setTag("down");
        tv.setText(malsseums.get(i).getMainCategory().toString());
        if(b) {
            iv.setTag("up");
            iv.setImageResource(R.drawable.drop_up);
        }else {
            iv.setTag("down");
            iv.setImageResource(R.drawable.drop_down);
        }
        return view;
    }

    @Override
    public View getChildView(int groupPos, int childPos, boolean b, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.malsseum_item_child,null);

        TextView tv = (TextView) convertView.findViewById(R.id.malsseum_item_child_textview);
        LinearLayout ll = (LinearLayout)convertView.findViewById(R.id.malsseum_item_child_ll);
        tv.setText(malsseums.get(groupPos).getCategory().get(childPos).toString());
        ll.setOnClickListener((view -> {
            Intent intent = new Intent(context,MalsseumContentActivity.class);
            intent.putExtra("title",malsseums.get(groupPos).getCategory().get(childPos));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }));

        return convertView;
    }

    @Override //자식선택여부
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
