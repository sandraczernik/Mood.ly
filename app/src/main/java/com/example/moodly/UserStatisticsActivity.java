package com.example.moodly;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;

import kotlin.Pair;

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
    private ArrayList<Pair<String, String>> countList = new ArrayList<>();

    private ArrayList<String> moodCountList = new ArrayList<>();
    String formattedDate;



    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;
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



        ArrayAdapter<Pair<String,String>>showAdapter;
        showAdapter
                = new ArrayAdapter<Pair<String,String>>(UserStatisticsActivity.this, R.layout.custom_dropdown, countList);
        countListView.setAdapter(showAdapter);
//
//        MaterialCalendarView calendarMonthView = findViewById(R.id.calendarMonthView);
//        calendarMonthView.setDateSelected(CalendarDay.today(), true);

        barChart = findViewById(R.id.idBarChart);
        barChart.isDrawValueAboveBarEnabled();
        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Total Moods Added");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);
        barChart.setDragEnabled(true);
        // adding color to our bar data set.




        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.getXAxis().setGranularity(1);

        barChart.getAxisLeft().setGranularityEnabled(true);
        barChart.getAxisLeft().setGranularity(1);
        barChart.getAxisRight().setGranularityEnabled(true);
        barChart.getAxisRight().setGranularity(1);

        final String[] labels = new String[] {"Angry", "Crying", "Happy", "Sad", "Unsure", "Very Happy",};
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));



        // sets colors for the dataset, resolution of the resource name to a "real" color is done internally
        barDataSet.setColors(new int[]{Color.parseColor("#C61313"),
                Color.parseColor("#4860B8"),
                Color.parseColor("#37A62B"),
                Color.parseColor("#50BAC8"),
                Color.parseColor("#30C697"),
                Color.parseColor("#105E14")});


        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
    }

    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();


        for (int i = 0; i < countList.size(); i++) {

            String key = countList.get(i).getFirst();
            String value = countList.get(i).getSecond();
            barEntriesArrayList.add(new BarEntry(i, Float.parseFloat(value)));

            System.out.println("KEY" + key);
            System.out.println("VALUE" + value);
        }


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