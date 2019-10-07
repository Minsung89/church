package com.ydn.church.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.ydn.church.Database.MalsseumHelper;
import com.ydn.church.Entity.Malsseum;
import com.ydn.church.Entity.MalsseumContent;
import com.ydn.church.MalsseumContentActivity;
import com.ydn.church.R;
import com.ydn.church.Util.AlarmUtil;

import java.util.Calendar;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    private final static int NOTICATION_ID = 222;


    MalsseumHelper mh;
    String DATABASE_NAME = "CHURCH";
    private String channelId = "channel";
    private String channelName = "channelName";


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("AlarmBroadcastReceiver", "onReceive");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //제목,페이지,notification 페이지번호, 일자
        String title = intent.getStringExtra("title");
        int page = intent.getIntExtra("page",1);
        int requestCode = intent.getIntExtra("requestCode",1);
        Calendar calendar = (Calendar) intent.getSerializableExtra("calendar");

        Log.i("AlarmBroadcastReceiver",calendar.getTimeInMillis()+"");

        //다음날같은시간
        calendar.add(Calendar.DATE,1);
//        calendar.add(Calendar.SECOND,60);

        //알림 울리면 다음날로 설정
        new AlarmUtil().setAlarm(context,requestCode,title,page,calendar);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        mh = new MalsseumHelper(context,DATABASE_NAME);
        String content = mh.getContent(title,page);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), channelId);
        Intent notificationIntent = new Intent(context.getApplicationContext(), MalsseumContentActivity.class); // 알림 클릭 시 이동할 액티비티 지정
        notificationIntent.putExtra("title",title);
        notificationIntent.putExtra("page",page);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        // FLAG_UPDATE_CURRENT : 이미 생성된 PendingIntent가 존재하면 해당 Intent의 Extra Data만 변경한다.
        //requestCode = 알람 개수
        PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentTitle("말씀묵상시간 ("+title+" "+page+"장)") //제목
        .setContentText(content) //내용
        .setDefaults(Notification.DEFAULT_ALL) //알림 설정(사운드, 진동)
        .setAutoCancel(true) //터치 시 자동으로 삭제할 지 여부
        .setPriority(Notification.PRIORITY_HIGH)
        .setPriority(NotificationCompat.PRIORITY_HIGH) // 알림의 중요도
        .setSmallIcon(R.drawable.main_contemplation)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.main_contemplation))
                .setContentIntent(pendingIntent);

        //NotificationCompat 스타일
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle(builder);
        style.bigText(content).setBigContentTitle("말씀묵상시간 "+title+" "+page+"장");

        notificationManager.notify(NOTICATION_ID+requestCode, builder.build());

    }



}
