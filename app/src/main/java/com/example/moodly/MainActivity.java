package com.example.moodly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    //navigation
    public BottomNavigationItemView bottomNavigationItemView;
    public FloatingActionButton floatingActionButton;
    public TextView textView;
    CalendarDay currentDay;
    int year;
    int month;
    int day;
    DBHandler DB;
    MaterialCalendarView materialCalView;
    String currentDate;
    // displaying list
    String formattedDate;
    private ArrayList<String> moodList = new ArrayList<>();
    ListView moodListView;

    Drawable veryHappyImage;
    String veryhappyBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = getIntent();
        formattedDate = intent.getStringExtra("key");
        System.out.println("DATE PASSED " +formattedDate);

        DB = new DBHandler(MainActivity.this);
        MaterialCalendarView materialCalView = findViewById(R.id.calendarView);
        materialCalView.setDateSelected(CalendarDay.today(), true);
        currentDay = materialCalView.getSelectedDate();
        year = currentDay.getYear();
        month = currentDay.getMonth();
        day = currentDay.getDay();




        String currentDate = String.format("%02d/%02d/%04d", day, month, year);


        moodList = DB.getDate(currentDate);
        moodListView = findViewById(R.id.moodListRecyclerView);

        ArrayAdapter<String> showAdapter;
        showAdapter
                = new ArrayAdapter<String>(MainActivity.this, R.layout.custom_dropdown, moodList);
        moodListView.setAdapter(showAdapter);
        //list of moods



        //image saving
        //getResources().getIdentifier("very_happy_smiley","drawable","com.app");
        // WORKS System.out.println(getResources().getIdentifier("very_happy_smiley","drawable","com.example.moodly"));



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
                        Intent intentCalendar = new Intent(MainActivity.this, UserStatisticsActivity.class);
                        intentCalendar.putExtra("key", formattedDate);
                        startActivity(intentCalendar);
                        return true;
                    case R.id.statsButton:
                        Intent intentStats = new Intent(MainActivity.this, UserProfileActivity.class);
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
                if ((FloatingActionButton) view == floatingActionButton) {
                    Intent intentAdd = new Intent(MainActivity.this, AddMoodActivity.class);
                    startActivity(intentAdd);
                }
            }
        });

// LATER

        materialCalView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                currentDay = materialCalView.getSelectedDate();
                year = currentDay.getYear();
                month = currentDay.getMonth();
                day = currentDay.getDay();



//                StringBuilder builder = new StringBuilder();
//                builder.append(day);
//                builder.append("/");
//                builder.append(month);
//                builder.append("/");
//                builder.append(year);
//                currentDate = builder.toString();
                //System.out.println(String.format("%02d/%02d/%04d", day, month, year));
                String currentDate = String.format("%02d/%02d/%04d", day, month, year);


                moodList = DB.getDate(currentDate);
                moodListView = findViewById(R.id.moodListRecyclerView);

                ArrayAdapter<String> showAdapter;
                showAdapter
                        = new ArrayAdapter<String>(MainActivity.this, R.layout.custom_dropdown, moodList);
                moodListView.setAdapter(showAdapter);
                ///get date
                //if date == date // loop
            }
        });
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }}








