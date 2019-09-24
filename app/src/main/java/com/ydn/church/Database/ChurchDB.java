package com.ydn.church.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ChurchDB {
    Context context;
    //SQLite DB
    SQLiteDatabase db;


    //SQLite(DB 이름)
    String DATABASE_NAME = "CHURCH";


    public ChurchDB(Context context){
        this.context = context;
    }
    //데이터베이스 접속 또는 만들기
    public void connectDatabase(){
        try {
            db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);
            Log.i("Database", "OK");
        }catch (Exception e){
            e.printStackTrace();
            Log.i("Database", "NO");
        }
    }

    //데이터베이스 테이블 만들기
    public void createTable(){
        db.execSQL("CREATE TABLE IF NOT EXISTS MALSSEUM(TITLE VARCHAR2(20) NOT NULL, SUB_TITLE VARCHAR2(20) NOT NULL, PAGE VARCHAR2(20) NOT NULL, SECTION VARCHAR2(20) NOT NULL, CONTENTS VARCHAR2(2000) NOT NULL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS RESPONSIVE_READING(SEQUENCE VARCHAR2(5), TITLE VARCHAR2(20) NOT NULL, CONTENTS VARCHAR2(2000) NOT NULL);");

    }

}
