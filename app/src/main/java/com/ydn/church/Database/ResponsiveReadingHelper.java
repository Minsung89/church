package com.ydn.church.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.ydn.church.Entity.ResponsiveReading;

import java.util.ArrayList;
import java.util.List;

public class ResponsiveReadingHelper extends SQLiteOpenHelper {
    String TABLE_NAME = "RESPONSIVE_READING";
    SQLiteDatabase db;
    ResponsiveReadingDataSet rrd = new ResponsiveReadingDataSet();
    public ResponsiveReadingHelper(Context context, String DATABASE_NAME) {

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
    public void insert() {
        try {
            db.beginTransaction();
            rrd.data1(db,TABLE_NAME);
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

    public List<ResponsiveReading> getData(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+";",null);
        int recordCount = cursor.getCount();
        if(recordCount < 1 ){
        }
        List<ResponsiveReading> rrs = new ArrayList<>();
        for (int i = 0 ; i < recordCount ; i++){
            cursor.moveToNext();
            ResponsiveReading rr = new ResponsiveReading();
            rr.setSequence(cursor.getString(0));
            rr.setTitle(cursor.getString(1));
            rr.setContents(cursor.getString(2));
            rrs.add(rr);
        }
        return rrs;

    }
}
