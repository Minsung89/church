package com.ydn.church.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ydn.church.Entity.MalsseumContent;

import java.util.ArrayList;
import java.util.List;


public class MalsseumHelper extends SQLiteOpenHelper {
    String TABLE_NAME = "MALSSEUM";
    SQLiteDatabase db;
    MalsseumDataSet1 ma = new MalsseumDataSet1();
    MalsseumDataSet2 ma2 = new MalsseumDataSet2();
    MalsseumDataSet3 ma3 = new MalsseumDataSet3();
    MalsseumDataSet4 ma4 = new MalsseumDataSet4();

    public MalsseumHelper(Context context, String DATABASE_NAME) {

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
   //RFID 스캔 저장
    public void insert() {
        try {
            db.beginTransaction();
            ma.data1(db,TABLE_NAME);
            ma.data2(db,TABLE_NAME);
            ma.data3(db,TABLE_NAME);
            ma.data4(db,TABLE_NAME);
            ma.data5(db,TABLE_NAME);

            ma2.data1(db,TABLE_NAME);
            ma2.data2(db,TABLE_NAME);
            ma2.data3(db,TABLE_NAME);
            ma2.data4(db,TABLE_NAME);
            ma2.data5(db,TABLE_NAME);

            ma3.data1(db,TABLE_NAME);
            ma3.data2(db,TABLE_NAME);
            ma3.data3(db,TABLE_NAME);
            ma3.data4(db,TABLE_NAME);
            ma3.data5(db,TABLE_NAME);

            ma4.data1(db,TABLE_NAME);
            ma4.data2(db,TABLE_NAME);
            ma4.data3(db,TABLE_NAME);
            ma4.data4(db,TABLE_NAME);
            ma4.data5(db,TABLE_NAME);

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }



    // subTitle
    public ArrayList<String> getSubTitle(String title){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT(SUB_TITLE) FROM "+TABLE_NAME+" WHERE TITLE = '"+title+"';",null);
        int recordCount = cursor.getCount();
        if(recordCount < 1 ){
            return null;
        }

        ArrayList<String> subTitles = new ArrayList<>();
        for (int i = 0 ; i < recordCount ; i++){
            cursor.moveToNext();
            subTitles.add(cursor.getString(0));
        }

        return subTitles;
    }

    public int getPageCount(String subTitle){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(DISTINCT(Page)) FROM "+TABLE_NAME+" WHERE SUB_TITLE = '"+subTitle+"';",null);
        for (int i = 0 ; i < cursor.getCount() ; i++){
            cursor.moveToNext();
            return Integer.valueOf(cursor.getString(0));
        }

        return 0;
    }

    public String getData(){
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
    public ArrayList<MalsseumContent> getContent(String subTitle, String page){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE SUB_TITLE = '"+subTitle+"' AND PAGE = '"+page+"';",null);
        int recordCount = cursor.getCount();
        if(recordCount < 1 ){
            return null;
        }

        ArrayList<MalsseumContent> malsseumContents = new ArrayList<>();
        for (int i = 0 ; i < recordCount ; i++){
            cursor.moveToNext();
            MalsseumContent mc = new MalsseumContent();
            mc.setTitle(cursor.getString(0));
            mc.setSubTitle(cursor.getString(1));
            mc.setPage(cursor.getString(2)+"장");
            mc.setSection(cursor.getString(3));
            mc.setContents(cursor.getString(4));

            malsseumContents.add(mc);
        }

        return malsseumContents;
    }
}

