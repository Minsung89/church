package com.ydn.church.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ImageView;

import com.ydn.church.Database.MalsseumHelper;
import com.ydn.church.Entity.MalsseumContent;
import com.ydn.church.Entity.ResponsiveReading;
import com.ydn.church.Fragment.MalsseumContentFragment;
import com.ydn.church.Fragment.ResponsiveReadingContentFragment;

import java.util.ArrayList;
import java.util.List;

public class ResponsiveReadingPagerAdapter extends FragmentStatePagerAdapter {

    Context context;
    List<ResponsiveReading> rrs;

    public ResponsiveReadingPagerAdapter(FragmentManager fm, List<ResponsiveReading> rrs) {
        super(fm);
        this.rrs = rrs;
    }

    @Override
    public Fragment getItem(int position) {


        return new ResponsiveReadingContentFragment(rrs.get(position));
    }

    @Override
    public int getCount() {
        return rrs.size();
    }

    public void enlargement(){}
    public void reduction(){}
}
