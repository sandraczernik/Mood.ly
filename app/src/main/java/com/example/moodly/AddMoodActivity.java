package com.example.moodly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddMoodActivity extends AppCompatActivity implements View.OnClickListener{

    //mood buttons
    Button veryHappyBtn;
    Button happyBtn;
    Button unsureBtn;
    Button sadBtn;
    Button cryingBtn;
    Button angryBtn;

    //navigation buttons
    Button backBtn;

    Button nextBtn;


    String selectedMood = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        veryHappyBtn = (Button) findViewById(R.id.veryHappyButton);
        happyBtn = (Button) findViewById(R.id.happyButton);
        unsureBtn = (Button) findViewById(R.id.unsureButton);
        sadBtn = (Button) findViewById(R.id.sadButton);
        cryingBtn = (Button) findViewById(R.id.cryingButton);
        angryBtn = (Button) findViewById(R.id.angryButton);

        backBtn = (Button) findViewById(R.id.addMoodBackButton);
        nextBtn = (Button) findViewById(R.id.addMoodNextButton);


    }
//    public static final String ANSI_RESET = "\u001B[0m";
//    public static final String ANSI_BLACK = "\u001B[30m";
//    public static final String ANSI_RED = "\u001B[31m";
//    public static final String ANSI_GREEN = "\u001B[32m";
//    public static final String ANSI_YELLOW = "\u001B[33m";
//    public static final String ANSI_BLUE = "\u001B[34m";
//    public static final String ANSI_PURPLE = "\u001B[35m";
//    public static final String ANSI_CYAN = "\u001B[36m";
//    public static final String ANSI_WHITE = "\u001B[37m";

    @Override
    public void onClick(View view) {

        if ((Button)view == veryHappyBtn){
            Log.i("Very Happy Button", "Very Happy Button Clicked");
            selectedMood =  "Very Happy";
            veryHappyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));

        }else{
            veryHappyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#105E14")));
        }

        if ((Button)view == happyBtn){
            Log.i("Happy Button", "Happy Button Clicked");
            selectedMood = "Happy";
            happyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }else{
            happyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#37A62B")));
        }

        if ((Button)view == unsureBtn){
            Log.i("Unsure Button", "Unsure Button Clicked");
            selectedMood = "Unsure";
            unsureBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }else{
            unsureBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#30C697")));
        }

        if ((Button)view == sadBtn){
            Log.i("Sad Button", "Sad Button Clicked");
            selectedMood = "Sad";
            sadBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }else{
            sadBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#50BAC8")));
        }

        if ((Button)view == cryingBtn){
            Log.i("Crying Button", "Crying Button Clicked");
            selectedMood = "Crying";
            cryingBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }else{
            cryingBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4860B8")));
        }

        if ((Button)view == angryBtn){
            Log.i("Angry Button", "Angry Button Clicked");
            selectedMood = "Angry";
            angryBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));

        }else{
            angryBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C61313")));
        }

        if ((Button)view == backBtn){
            Log.i("Back Button", "Back Button Clicked");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        if ((Button)view == nextBtn) {
            if (selectedMood != "") {
                Log.i("Next Button", "Next Button Clicked");
                Intent intent = new Intent(this, AddCategoryActivity.class);
                intent.putExtra("key", selectedMood);
                startActivity(intent);
            } else {

                Toast.makeText(getApplicationContext(), "Please select a mood", Toast.LENGTH_SHORT).show();

            }


        }

    }


}