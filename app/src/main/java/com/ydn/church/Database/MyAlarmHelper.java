package com.ydn.church.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ydn.church.Entity.MyAlarm;

import java.util.ArrayList;
import java.util.List;


public class MyAlarmHelper extends SQLiteOpenHelper {
    String TABLE_NAME = "MY_ALARM";
    SQLiteDatabase db;
    ResponsiveReadingDataSet rrd = new ResponsiveReadingDataSet();
    public MyAlarmHelper(Context context, String DATABASE_NAME) {

        super(context, DATABASE_NAME, null, 1);
        db = getReadableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    //db version upgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void update(String item, int price) {

    }
    public void insert(String sequence, String title, String page, String alarmTime, String alarmYn) {
        try {
            db.beginTransaction();
            db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES('"+sequence+"', '"+title +"', '"+page+"', '"+alarmTime+"', '"+alarmYn+"'); ");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }


    public String getCount(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM "+TABLE_NAME+";",null);
        int recordCount = cursor.getCount();
        if(recordCount < 1 ){
        }
        String getCount = "";
        for (int i = 0 ; i < recordCount ; i++){
            cursor.moveToNext();
            getCount = cursor.getString(0);
            Log.i("getCount", getCount);
        }
        return getCount;

    }


    public String getSequence() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SEQUENCE FROM "+TABLE_NAME+" ORDER BY SEQUENCE DESC LIMIT 1;",null);
        int recordCount = cursor.getCount();
        if(recordCount < 1 ){
            return String.valueOf(recordCount);
        }
        String sequence = "";
        for (int i = 0 ; i < recordCount ; i++){
            cursor.moveToNext();
            sequence = cursor.getString(0);
        }
        return sequence;

    }

    public List<MyAlarm> getData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + ";", null);
        int recordCount = cursor.getCount();
        if (recordCount < 1) {
            return null;
        }
        List<MyAlarm> myAlarms = new ArrayList<>();
        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();
            MyAlarm ma = new MyAlarm();
            ma.setSequence(cursor.getString(0));
            ma.setSubTitle(cursor.getString(1));
            ma.setPage(cursor.getString(2));
            ma.setAlarmTime(cursor.getString(3));
            ma.setAlarmYn(cursor.getString(4));
            myAlarms.add(ma);
        }
        return myAlarms;
    }
}
