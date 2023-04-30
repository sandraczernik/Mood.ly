package com.example.moodly;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kotlin.Pair;


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
                "activities TEXT," +
                "moodDate TEXT," +
                "moodCalendarDate TEXT)"
        );

//        MyDB.execSQL("create Table if not exists moodImages(" +
//                "moodImageType TEXT PRIMARY KEY AUTOINCREMENT," +
//                "moodImage BLOB)"
//
//        );

    }

//TODO: add new categories + display on main page
    public Boolean insertNewMood (String moodType, List<String> activities, String moodDate, String moodCalendarDate){

        StringBuilder builder = new StringBuilder();
        String seperator = ", ";
        for (int i =0; i < activities.size();i++){

            builder.append(seperator).append(activities.get(i));

        }
        System.out.println("Activity size: " + activities.size());

        String activityResult = builder.substring(seperator.length());
        System.out.println(activityResult);
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("moodType", moodType);
        contentValues.put("activities", activityResult);
        contentValues.put("moodDate", moodDate);
        contentValues.put("moodCalendarDate", moodCalendarDate);
        long result = MyDB.insert("moods", null, contentValues);
        System.out.println("result" + result);
        if (result == -1) return false;
        else
            return  true;
    }

    public ArrayList<String> getMoodList() {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "SELECT moodType, activities, moodDate FROM moods";
        Cursor cursorMoodList = MyDB.rawQuery(query,null);
        ArrayList<String> listMoods = new ArrayList<>();

        if (cursorMoodList.moveToFirst()){
            do {
                String currentMoodType = cursorMoodList.getString(0);
                String currentActivity = cursorMoodList.getString(1);
                String currentMoodTime = cursorMoodList.getString(2);
                String moodEntry = currentMoodType + "\n" + currentActivity + "\n" + currentMoodTime;
                listMoods.add(moodEntry);
            } while (cursorMoodList.moveToNext());
        } cursorMoodList.close();
        return listMoods;
    }

    public ArrayList<String> getDate(String currentDate) {
        System.out.println("HELLO i am" + currentDate);
        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "SELECT moodType, activities, moodDate FROM moods WHERE moodCalendarDate =" + "'" + currentDate + "'";
        Cursor cursorMoodList = MyDB.rawQuery(query,null);
        ArrayList<String> listMoods = new ArrayList<>();

        if (cursorMoodList.moveToFirst()){
            do {
                String currentMoodType = cursorMoodList.getString(0);
                String currentActivity = cursorMoodList.getString(1);
                String currentMoodTime = cursorMoodList.getString(2);
                String moodEntry = currentMoodType + "\n" + currentActivity + "\n" + currentMoodTime;
                listMoods.add(moodEntry);
                System.out.print("MOOD ENTRY" + moodEntry);
            } while (cursorMoodList.moveToNext());
        } cursorMoodList.close();
        return listMoods;

    }



    public ArrayList<Pair<String,String> >  getTypeAmount() {

        SQLiteDatabase MyDB = this.getReadableDatabase();
        String query = "SELECT  moodType ,count(moodType) FROM moods GROUP BY moodType ";
        Cursor cursor = MyDB.rawQuery(query,null);

        ArrayList<Pair<String,String>> listCount = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {

                String currentMoodType = cursor.getString(0);
                String currentCount = cursor.getString(1);
                Pair<String, String> pair = new Pair<>(currentMoodType,currentCount);
                String moodEntry = currentMoodType + "\n" + currentCount;
                listCount.add(pair);
                System.out.print("MOOD ENTRY" + moodEntry);
            } while (cursor.moveToNext());
        } cursor.close();
        return listCount;
    }




    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists moods");

    }



}
