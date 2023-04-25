package com.example.moodly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public BottomNavigationItemView bottomNavigationItemView;

    public TextView textView;
    public FloatingActionButton floatingActionButton;
    DBHandler DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHandler(MainActivity.this);


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


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        String month_name = month_date.format(calendar.getTime());
        textView = findViewById(R.id.monthDisplay);
        textView.setText(month_name);











    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }}








