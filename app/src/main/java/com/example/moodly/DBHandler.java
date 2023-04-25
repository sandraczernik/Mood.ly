package com.example.moodly;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class DBHandler extends SQLiteOpenHelper {
    public static final String DBNAME = "Mood.ly_App";
    //creating DB handler which is used on all pages with database functions
    public DBHandler(Context context) {
        super(context, DBNAME, null, 1);
        SQLiteDatabase MYDB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table if not exists moods(" +
                "moodID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "moodType TEXT," +
                "categories TEXT," +
                "mmoodDate TEXT)"
        );

        }






    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists moods");

    }


}
