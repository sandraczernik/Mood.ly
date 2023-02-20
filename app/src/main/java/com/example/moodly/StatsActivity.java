package com.example.moodly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class StatsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    public BottomNavigationItemView bottomNavigationItemView;
    public FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);


        bottomNavigationItemView =  findViewById(R.id.homeButton);
        bottomNavigationItemView = findViewById(R.id.calendarButton);
        floatingActionButton = findViewById(R.id.addButton);
        bottomNavigationItemView =  findViewById(R.id.statsButton);
        bottomNavigationItemView =  findViewById(R.id.moreButton);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeButton:
                        Intent intentHome = new Intent(StatsActivity.this , MainActivity.class);
                        startActivity(intentHome);
                        return true;
                    case R.id.calendarButton:
                        Intent intentCalendar = new Intent(StatsActivity.this , CalendarActivity.class);
                        startActivity(intentCalendar);
                        return true;
                    case R.id.addButton:
                        Intent intentAdd = new Intent(StatsActivity.this , AddMoodActivity.class);
                        startActivity(intentAdd);
                        return true;
                    case R.id.statsButton:
                        Intent intentStats = new Intent(StatsActivity.this , StatsActivity.class);
                        startActivity(intentStats);
                        return true;
                    case R.id.moreButton:
                        Intent intentMore = new Intent(StatsActivity.this , MoreActivity.class);
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
                    Intent intentAdd = new Intent(StatsActivity.this, AddMoodActivity.class);
                    startActivity(intentAdd);
                }}
        });
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}