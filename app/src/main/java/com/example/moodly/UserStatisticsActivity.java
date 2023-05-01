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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

import kotlin.Pair;

public class UserStatisticsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    //Navigation
    public BottomNavigationItemView bottomNavigationItemView;
    public BottomNavigationView bottomNavigationView;
    public FloatingActionButton floatingActionButton;

    //Database
    DBHandler DB;

    //ListView & PieChart
    ListView countListView;
    private ArrayList<Pair<String, String>> countList = new ArrayList<>();
    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataset;
    ArrayList<PieEntry> pieEntriesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userstatistics);
        //Database
        DB = new DBHandler(UserStatisticsActivity.this);
        //Getting query with count() so total moods can be displayed
        countList = DB.getTypeAmount();

        //XML to variable
        countListView = findViewById(R.id.countListView);
        bottomNavigationItemView =  findViewById(R.id.homeButton);
        bottomNavigationItemView = findViewById(R.id.statisticsButton);
        floatingActionButton = findViewById(R.id.addButton);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // creating a new array list to display total amount of moods added with PAIR of values using
        // .getFirst() and .getSecond() for each item in database
        ArrayList<String> values = new ArrayList<>();
            for (int i = 0; i < countList.size(); i++) {
                String key = countList.get(i).getFirst();
                String value = countList.get(i).getSecond();
                values.add(key + " " + value);
            }
            //Attaching count method from database to list view
        ArrayAdapter<String>showAdapter;
            showAdapter
                    = new ArrayAdapter<String>(UserStatisticsActivity.this, R.layout.custom_dropdown, values);
            countListView.setAdapter(showAdapter);

            //creating array list specifically for pie entries so they can be later attached to a label so it is visible on pie chart
        ArrayList<PieEntry> values2 = new ArrayList<>();
            for (int i = 0; i < countList.size(); i++) {
                String key = countList.get(i).getFirst();
                String value = countList.get(i).getSecond();
                values2.add(new PieEntry(Float.parseFloat(value),key));
            }

            //Customization methods for pie chart
            pieDataset = new PieDataSet(values2, "");
            pieDataset.setColors(ColorTemplate.PASTEL_COLORS);
            pieChart = findViewById(R.id.piechart);
            pieChart.setDrawHoleEnabled(false);
            pieData = new PieData(pieDataset);
            pieChart.setData(pieData);
            pieChart.setNoDataText("Please add a mood to view the chart");
            pieChart.setBackgroundColor(Color.parseColor("#EDE1D2"));
            pieDataset.setValueTextSize(16f);
            pieChart.getDescription().setEnabled(false);

            getPieEntries();
        }

    private void getPieEntries() {
        // creating a new array list
        pieEntriesArrayList = new ArrayList<>();
        for (int i = 0; i < countList.size(); i++) {
            String key = countList.get(i).getFirst();
            String value = countList.get(i).getSecond();
            //Attaching the values and keys from database to their respective legends for readability
            pieEntriesArrayList.add(new PieEntry(Float.parseFloat(value), i));
            Legend legend = pieChart.getLegend();
            legend.setWordWrapEnabled(true);
            System.out.println(legend);
            legend.setEnabled(true);
        }
        pieChart.getLegend().setEnabled(false);
        //Navigation set-up
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeButton:
                        Intent intentHome = new Intent(UserStatisticsActivity.this , MainActivity.class);
                        startActivity(intentHome);
                        return true;
                    case R.id.statisticsButton:
                        Intent intentCalendar = new Intent(UserStatisticsActivity.this , UserStatisticsActivity.class);
                        startActivity(intentCalendar);
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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}