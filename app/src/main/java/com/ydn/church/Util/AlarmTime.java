package com.ydn.church.Util;

import android.util.Log;

import java.util.Calendar;

public class AlarmTime {

    public Calendar setAlarmTime(int h, int m){
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.HOUR_OF_DAY) <= h && calendar.get(Calendar.MINUTE) <= m)
            calendar.add(Calendar.DATE,1);
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE,m);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND, 0);

        Log.i("AlarmTime",calendar.getTimeInMillis()+"");
        return calendar;
    }
}
