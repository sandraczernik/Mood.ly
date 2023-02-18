package com.example.moodly;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DBNAME = "Mood.ly_App";
    //creating DB handler which is used on all pages with database functions
    public DBHandler(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table if not exists users(" +
                "userID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "firstName TEXT, " +
                "lastName TEXT, " +
                "username TEXT, " +
                "userEmail TEXT, " +
                "password TEXT, " +
                "dateofbirth TEXT)"
        );
        }


    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }
}
