package com.example.moodly;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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
                "moodDate TEXT)"
        );

        }

//TODO: add new categories + display on main page
    public Boolean insertNewMood (String moodType, List<String> activities, String moodDate){

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

                String moodEntry = currentMoodType + " | " + currentActivity + " | " + currentMoodTime;
                listMoods.add(moodEntry);
            } while (cursorMoodList.moveToNext());
        } cursorMoodList.close();
        return listMoods;
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists moods");

    }



}
