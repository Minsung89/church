package com.ydn.church.Util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.ydn.church.Service.AlarmBroadcastReceiver;

import java.util.Calendar;

public class AlarmUtil {
    private static final long FIVE_TO_HOUR = 1000 * 10 * 1; // 5분

    // 알람 추가 메소드 (requestCode = 알람 개수)
    public static void setAlarm(Context context, int requestCode, String title, int page, Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        intent.putExtra("title", title);
        intent.putExtra("page", page);
        intent.putExtra("requestCode", requestCode);
        intent.putExtra("calendar", calendar);
        Log.i("AlarmUtil", calendar.getTimeInMillis() + "");
        Log.i("AlarmUtilNow", System.currentTimeMillis() + "");
        // FLAG_CANCEL_CURRENT : 이전에 생성한 PendingIntent 는 취소하고 새롭게 만든다
        PendingIntent padPendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Log.i("내껀 뭐지?", 1 + "인가?");
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), padPendingIntent);
        // alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(getTriggerAtMillis(hourOfDay, minute), alarmPendingIntent), alarmPendingIntent);
        // 이전 포스팅 참고
        }else if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {

            Log.i("내껀 뭐지?", 2+ "인가?");
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), padPendingIntent);
        }else{

            Log.i("내껀 뭐지?", 3 + "인가?");
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), padPendingIntent);
        }


    }

    //알람 해제 메소드
    public static void releaseAlarm(Context context, int requestCode){
        AlarmManager fiveToHourAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        Intent fiveIntent = new Intent(context, AlarmBroadcastReceiver.class);

        PendingIntent fiveSender = PendingIntent.getBroadcast(context, requestCode, fiveIntent, 0);

        fiveToHourAlarmManager.cancel(fiveSender);

        Log.d("NotiTEST", "AlarmUtil Canel");
    }


}
