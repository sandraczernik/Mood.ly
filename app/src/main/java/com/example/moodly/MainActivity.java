package com.example.moodly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    //Navigation
    public BottomNavigationItemView bottomNavigationItemView;
    public BottomNavigationView bottomNavigationView;
    public FloatingActionButton floatingActionButton;

    //Calendar
    CalendarDay currentDay;
    int year;
    int month;
    int day;
    String formattedDate;

    //Database
    DBHandler DB;

    //Mood list for listView
    private ArrayList<String> moodList = new ArrayList<>();
    ListView moodListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Database setup
        DB = new DBHandler(MainActivity.this);

        //Receiving intent
        Intent intent = getIntent();
        formattedDate = intent.getStringExtra("key");

        //Assigning XML elements to variables
        bottomNavigationItemView = findViewById(R.id.homeButton);
        bottomNavigationItemView = findViewById(R.id.statisticsButton);
        floatingActionButton = findViewById(R.id.addButton);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //Calendar set-up
        MaterialCalendarView materialCalView = findViewById(R.id.calendarView);
        materialCalView.setDateSelected(CalendarDay.today(), true);
        currentDay = materialCalView.getSelectedDate();
        year = currentDay.getYear();
        month = currentDay.getMonth();
        day = currentDay.getDay();
        String currentDate = String.format("%02d/%02d/%04d", day, month, year);
        moodList = DB.getDate(currentDate);
        moodListView = findViewById(R.id.moodListRecyclerView);

        //Adapter attaches database list to the listView
        ArrayAdapter<String> showAdapter;
        showAdapter
                = new ArrayAdapter<String>(MainActivity.this, R.layout.custom_dropdown, moodList);
        moodListView.setAdapter(showAdapter);

        //Listener for bottom navigation
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeButton:
                        Intent intentHome = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        return true;
                    case R.id.statisticsButton:
                        Intent intentCalendar = new Intent(MainActivity.this, UserStatisticsActivity.class);
                        startActivity(intentCalendar);
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

        //Calendar set-up to link up to database and show entries based on the day the user adds a mood
        materialCalView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                currentDay = materialCalView.getSelectedDate();
                year = currentDay.getYear();
                month = currentDay.getMonth();
                day = currentDay.getDay();
                String currentDate = String.format("%02d/%02d/%04d", day, month, year);

                //Changes when calendar element is pressed for
                moodList = DB.getDate(currentDate);
                moodListView = findViewById(R.id.moodListRecyclerView);

                //Attaches database to listview
                ArrayAdapter<String> showAdapter;
                showAdapter
                        = new ArrayAdapter<String>(MainActivity.this, R.layout.custom_dropdown, moodList);
                moodListView.setAdapter(showAdapter);
            }
        });
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }}