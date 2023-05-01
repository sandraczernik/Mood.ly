package com.example.moodly;

import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;
import java.util.Objects;

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

        PieChart pieChart;

    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;
        PieData pieData;
    PieDataSet pieDataset;
    // array list for storing entries.
    ArrayList<PieEntry> pieEntriesArrayList;
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


            // creating a new array list
        ArrayList<String> values = new ArrayList<>();
            for (int i = 0; i < countList.size(); i++) {

                String key = countList.get(i).getFirst();
                String value = countList.get(i).getSecond();
                values.add(key + " " + value);

//
//                if (countList.get(i).getFirst() == "Angry") {
//
//                }
                System.out.println("KEY" + key);
                System.out.println("VALUE" + value);

            }

        ArrayAdapter<String>showAdapter;
        showAdapter
                = new ArrayAdapter<String>(UserStatisticsActivity.this, R.layout.custom_dropdown, values);
        countListView.setAdapter(showAdapter);
//
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#C61313"));
        colors.add(Color.parseColor("#4860B8"));
        colors.add(Color.parseColor("#37A62B"));
        colors.add(Color.parseColor("#50BAC8"));
        colors.add(Color.parseColor("#30C697"));
        colors.add( Color.parseColor("#105E14"));



//        MaterialCalendarView calendarMonthView = findViewById(R.id.calendarMonthView);
//        calendarMonthView.setDateSelected(CalendarDay.today(), true);
        ArrayList<PieEntry> values2 = new ArrayList<>();
        for (int i = 0; i < countList.size(); i++) {

            String key = countList.get(i).getFirst();
            String value = countList.get(i).getSecond();
            values2.add(new PieEntry(Float.parseFloat(value),key));

//
//                if (countList.get(i).getFirst() == "Angry") {
//
//                }
            System.out.println("KEY" + key);
            System.out.println("VALUE" + value);

        }
        pieDataset = new PieDataSet(values2, "");
        pieDataset.setColors(ColorTemplate.PASTEL_COLORS);
        pieChart = findViewById(R.id.piechart);
        pieChart.setDrawHoleEnabled(false);
        // calling method to get bar entries.

        getBarEntries();
//        ArrayList<String > labels = new ArrayList<String>();
//        labels.add("Angry");
//        labels.add("Crying");
//        labels.add("Happy");
//        labels.add("Sad");
//        labels.add("Unsure");
//        labels.add("Very Happy");

        // creating a new bar data set.


        // creating a new bar data and
        // passing our bar data set.



        pieData = new PieData(pieDataset);


        // below line is to set data
        // to our bar chart.


        pieChart.setData(pieData);
        // adding color to our bar data set.

        pieChart.setNoDataText("Please add a mood to view the chart");

        pieChart.setBackgroundColor(Color.parseColor("#EDE1D2"));
        // setting text color.
        pieDataset.setValueTextColor(Color.BLACK);

//        final String[] labels = new String[] {"Angry", "Crying", "Happy", "Sad", "Unsure", "Very Happy",};
//        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));



//         //sets colors for the dataset, resolution of the resource name to a "real" color is done internally
//        pieDataset.setColors(new int[]{Color.parseColor("#C61313"),
//                Color.parseColor("#4860B8"),
//                Color.parseColor("#37A62B"),
//                Color.parseColor("#50BAC8"),
//                Color.parseColor("#30C697"),
//                Color.parseColor("#105E14")});
        //pieDataset.setColors(ColorTemplate.PASTEL_COLORS);
//        pieDataset.setColors(ColorTemplate.PASTEL_COLORS);
        // setting text size
        pieDataset.setValueTextSize(16f);
        pieChart.getDescription().setEnabled(false);

        for (int i = 0; i < countList.size(); i++) {

            System.out.println(values2.get(i).getValue());
            System.out.println(values2.get(i).getLabel());

        }













    }

    private void getBarEntries() {
        // creating a new array list
        pieEntriesArrayList = new ArrayList<>();


        for (int i = 0; i < countList.size(); i++) {

            String key = countList.get(i).getFirst();
            String value = countList.get(i).getSecond();
            pieEntriesArrayList.add(new PieEntry(Float.parseFloat(value), i));
            Legend legend = pieChart.getLegend();





            legend.setWordWrapEnabled(true);
            System.out.println(legend);
            legend.setEnabled(true);
            System.out.println("KEY" + key);
            System.out.println("VALUE" + value);
        }

        pieChart.getLegend().setEnabled(false);



        bottomNavigationItemView =  findViewById(R.id.homeButton);
        bottomNavigationItemView = findViewById(R.id.calendarButton);
        floatingActionButton = findViewById(R.id.addButton);

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