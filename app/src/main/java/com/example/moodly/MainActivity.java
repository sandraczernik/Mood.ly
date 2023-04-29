package com.example.moodly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    //navigation
    public BottomNavigationItemView bottomNavigationItemView;
    public FloatingActionButton floatingActionButton;
    public TextView textView;

    DBHandler DB;


    // displaying list
    private ArrayList<String> moodList = new ArrayList<>();
    ListView moodListView;
    Drawable veryHappyImage;
String veryhappyBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHandler(MainActivity.this);

        //list of moods
        this.moodList = DB.getMoodList();
        moodListView = findViewById(R.id.moodListRecyclerView);

        ArrayAdapter<String> showAdapter;
        showAdapter
                = new ArrayAdapter<String>(this, R.layout.custom_dropdown, moodList);
        moodListView.setAdapter(showAdapter);

        //image saving
        //getResources().getIdentifier("very_happy_smiley","drawable","com.app");
        // WORKS System.out.println(getResources().getIdentifier("very_happy_smiley","drawable","com.example.moodly"));
        MaterialCalendarView materialCalView = findViewById(R.id.calendarView);
        materialCalView.setDateSelected(CalendarDay.today(), true);

        //navigation
        bottomNavigationItemView = findViewById(R.id.homeButton);
        bottomNavigationItemView = findViewById(R.id.calendarButton);
        floatingActionButton = findViewById(R.id.addButton);
        bottomNavigationItemView = findViewById(R.id.statsButton);
        bottomNavigationItemView = findViewById(R.id.moreButton);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeButton:
                        Intent intentHome = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        return true;
                    case R.id.calendarButton:
                        Intent intentCalendar = new Intent(MainActivity.this, CalendarActivity.class);
                        startActivity(intentCalendar);
                        return true;
                    case R.id.statsButton:
                        Intent intentStats = new Intent(MainActivity.this, StatsActivity.class);
                        startActivity(intentStats);
                        return true;
                    case R.id.moreButton:
                        Intent intentMore = new Intent(MainActivity.this, MoreActivity.class);
                        startActivity(intentMore);
                        return true;
                }
                return false;
            }

        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if ((FloatingActionButton)view == floatingActionButton){
                Intent intentAdd = new Intent(MainActivity.this, AddMoodActivity.class);
                startActivity(intentAdd);
            }}
        });

// LATER
        Calendar calendar = Calendar.getInstance();





    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }}








