package com.example.moodly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    DBHandler DB;
    String key;

    List<String> activities = new ArrayList<>();
    CheckBox readingchk;
    CheckBox gamingchk;
    Button activityBtn;
    String formattedTime;
    String time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        readingchk = (CheckBox) findViewById(R.id.readingCheckbox);
        gamingchk = (CheckBox) findViewById(R.id.gamingCheckbox);
        activityBtn = (Button) findViewById(R.id.addActivityButton);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        System.out.println(key);

        DB = new DBHandler(AddCategoryActivity.this);

        Calendar calendar = Calendar.getInstance();
//       int hour = calendar.get(Calendar.HOUR);
//       int minute = calendar.get(Calendar.MINUTE);
        //formattedTime = (Integer.parseInt(Integer.toString(hour)) + ":" + Integer.parseInt(Integer.toString(minute)));
        //System.out.print(formattedTime);

        DateFormat df = new SimpleDateFormat("HH:mm");
        String time = df.format(Calendar.getInstance().getTime());
        formattedTime = time.toString();
        System.out.println(formattedTime);

    }

    @Override
    public void onClick(View view) {
        if ((Button) view == activityBtn) {



            String resulttest = "";
            Toast.makeText(getApplicationContext(), resulttest, Toast.LENGTH_SHORT).show();
            System.out.println(resulttest);

            Boolean insertMood = DB.insertNewMood(key, activities, formattedTime);
            if (insertMood){
               String moodAdded = "A new mood has been added";
                Toast.makeText(getApplicationContext(), moodAdded, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }




        }
    }

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