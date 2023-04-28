package com.example.moodly;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

//handles all database methods
public class DBHandler extends SQLiteOpenHelper {
    public static final String DBNAME = "Mood.ly_App";
    //creating DB handler which is used on all pages with database functions
    public DBHandler(Context context) {
        super(context, DBNAME, null, 1);
        SQLiteDatabase MyDB = this.getWritableDatabase();
    }

    // creating table to store all information added by user on AddMoodActivity.java
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table if not exists moods(" +
                "moodID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "moodType TEXT," +
                "categories TEXT," +
                "moodDate TEXT)"
        );

        }

//TODO: add new categories + display on main page
    // insert new mood and relevant categories into the database based on user input
    public Boolean insertNewMood;
    SQLiteDatabase MyDB = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();



    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists moods");

    }







}
