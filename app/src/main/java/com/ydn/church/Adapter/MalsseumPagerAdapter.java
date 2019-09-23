package com.ydn.church.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydn.church.Database.MalsseumHelper;
import com.ydn.church.Entity.MalsseumContent;
import com.ydn.church.Fragment.MalsseumContentFragment;

import java.util.ArrayList;
import java.util.List;

public class MalsseumPagerAdapter extends FragmentStatePagerAdapter {

    MalsseumHelper mh;
    Context context;
    int fmSize = 0;
    String subTitle;
    ImageView malsseumContentEnlargement, malsseumContentReduction;

    public MalsseumPagerAdapter(FragmentManager fm, int fmSize, MalsseumHelper mh , String subTitle, ImageView malsseumContentEnlargement,ImageView malsseumContentReduction) {
        super(fm);
        this.fmSize = fmSize;
        this.mh = mh;
        this.subTitle = subTitle;
        this.malsseumContentReduction = malsseumContentReduction;
        this.malsseumContentEnlargement = malsseumContentEnlargement;
    }

    @Override
    public Fragment getItem(int position) {

        ArrayList<MalsseumContent> mcs = mh.getContent(subTitle,String.valueOf(position+1));

        return new MalsseumContentFragment(mcs, malsseumContentEnlargement, malsseumContentReduction);
    }

    @Override
    public int getCount() {
        return fmSize;
    }

    public void enlargement(){}
    public void reduction(){}
}
