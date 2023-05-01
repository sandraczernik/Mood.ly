package com.example.moodly;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    //Database
    DBHandler DB;

    //Dates & Time
    String formattedDate;
    String formattedTime;

    //Buttons
    Button activityBtn;
    Button backBtn;

    //Intent from previous activity
    String key;

    //Array list to save to DB
    List<String> activities = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        //Database set-up
        DB = new DBHandler(AddCategoryActivity.this);

        //Assigning XML elements to variables
        activityBtn = (Button) findViewById(R.id.addActivityButton);
        backBtn = (Button) findViewById(R.id.addMoodBackButton2);

        //Retrieving intent from previous activity
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
    }

    //On-click for buttons
    @Override
    public void onClick(View view) {

        //Back button
        if ((Button)view == backBtn){
            Log.i("Back Button", "Back Button Clicked");
            Intent intent = new Intent(this, AddMoodActivity.class);
            startActivity(intent);
        }

        //If button is pressed & activities is NOT empty
        if ((Button) view == activityBtn && !activities.isEmpty()) {

            //Get date for database & calendar
            DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            String date = dateFormatter.format(Calendar.getInstance().getTime());
            formattedDate = date.toString();

            //Get time for database & to display
            DateFormat df = new SimpleDateFormat("HH:mm");
            String time = df.format(Calendar.getInstance().getTime());
            formattedTime = time.toString();

            //Insert mood & activities (mood retrieved from intent)
            Boolean insertMood = DB.insertNewMood(key, activities, formattedTime, formattedDate);
            if (insertMood){
               String moodAdded = "A new mood has been added";
                Toast.makeText(getApplicationContext(), moodAdded, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("key", formattedDate);
                startActivity(intent);
            }
        }
        //Cannot add mood & activities
        else {
                Toast.makeText(getApplicationContext(),"Please select at least one activity",Toast.LENGTH_LONG).show();
        }
    }

    //Checking which activities have been clicked & adding each to array list for database storage
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str="";
        // Check which checkbox was clicked
        if (view.getId() == R.id.readingCheckbox) {
            activities.add("Reading");
        }
        if (view.getId() == R.id.gamingCheckbox) {
            activities.add("Gaming");
        }
        if (view.getId() == R.id.exerciseCheckbox) {
            activities.add("Exercise");
        }
        if (view.getId() == R.id.dogCheckbox) {
            activities.add("Dog");
        }
        if (view.getId() == R.id.catCheckbox) {
            activities.add("Cat");
        }
        if (view.getId() == R.id.shoppingCheckox) {
            activities.add("Shopping");
        }
        if (view.getId() == R.id.healthyEatingCheckbox) {
            activities.add("Healthy Eating");
        }
        if (view.getId() == R.id.earlySleepCheckbox) {
            activities.add("Early Sleep");
        }
        if (view.getId() == R.id.cinemaCheckbox) {
            activities.add("Cinema");
        }
        if (view.getId() == R.id.relaxCheckbox) {
            activities.add("Relaxing");
        }
        if (view.getId() == R.id.familyCheckbox) {
            activities.add("Family");
        }
        if (view.getId() == R.id.friendsCheckbox) {
            activities.add("Friends");
        }
        if (view.getId() == R.id.programmingCheckbox) {
            activities.add("Programming");
        }
        if (view.getId() == R.id.unhealthyEatingCheckbox) {
            activities.add("Unhealthy Eating");
        }
        if (view.getId() == R.id.cleaningCheckbox) {
            activities.add("Cleaning");
        }
    }
}
