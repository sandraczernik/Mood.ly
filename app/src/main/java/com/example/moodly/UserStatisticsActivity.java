package com.example.moodly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;

public class UserStatisticsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    public BottomNavigationItemView bottomNavigationItemView;
    public FloatingActionButton floatingActionButton;
    CalendarDay currentDay;
    String currentDate;
    TextView testTextView;
    DBHandler DB;
    TextView test;
    int year;
    int month;
    int day;
    MaterialCalendarView calendarMonthView;
    ListView countListView;
    ListView testing;
    private ArrayList<String> countList = new ArrayList<>();

    private ArrayList<String> moodCountList = new ArrayList<>();
    String formattedDate;
    private String testList ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userstatistics);

        Intent intent = getIntent();
        formattedDate = intent.getStringExtra("key");
        System.out.println("DATE PASSED " +formattedDate);
        DB = new DBHandler(UserStatisticsActivity.this);







        countList = DB.getTypeAmount();
        countListView = findViewById(R.id.countListView);

        ArrayAdapter<String> showAdapter;
        showAdapter
                = new ArrayAdapter<String>(UserStatisticsActivity.this, R.layout.custom_dropdown, countList);
        countListView.setAdapter(showAdapter);
//
//        MaterialCalendarView calendarMonthView = findViewById(R.id.calendarMonthView);
//        calendarMonthView.setDateSelected(CalendarDay.today(), true);



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
                        Intent intentHome = new Intent(UserStatisticsActivity.this , MainActivity.class);
                        startActivity(intentHome);
                        return true;
                    case R.id.calendarButton:
                        Intent intentCalendar = new Intent(UserStatisticsActivity.this , UserStatisticsActivity.class);
                        startActivity(intentCalendar);
                        return true;
                    case R.id.addButton:
                        Intent intentAdd = new Intent(UserStatisticsActivity.this , AddMoodActivity.class);
                        startActivity(intentAdd);
                        return true;
                    case R.id.statsButton:
                        Intent intentStats = new Intent(UserStatisticsActivity.this , UserProfileActivity.class);
                        startActivity(intentStats);
                        return true;
                    case R.id.moreButton:
                        Intent intentMore = new Intent(UserStatisticsActivity.this , MoreActivity.class);
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
                    Intent intentAdd = new Intent(UserStatisticsActivity.this, AddMoodActivity.class);
                    startActivity(intentAdd);
                }}
        });


//        calendarMonthView.setOnDateChangedListener(new OnDateSelectedListener() {
//            @Override
//            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected)
//            {
//                currentDay = calendarMonthView.getSelectedDate();
//                year = currentDay.getYear();
//                month = currentDay.getMonth();
//                day = currentDay.getDay();
//                currentDate = String.format("%02d/%02d/%04d", day, month, year);
//                Intent goToMainCalendar = new Intent(UserStatisticsActivity.this, MainActivity.class);
//                startActivity(goToMainCalendar);
//
//            }
//        });
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}