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

    @Override
    public void onClick(View view) {

        if ((Button)view == veryHappyBtn){
            Log.i("Very Happy Button", "Very Happy Button Clicked");
            selectedMood = "Very Happy";
            veryHappyBtn.setBackgroundResource(R.drawable.very_happy_smiley_selected);
            veryHappyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));

        }else{
            veryHappyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#105E14")));
        }

        if ((Button)view == happyBtn){
            Log.i("Happy Button", "Happy Button Clicked");
            selectedMood = "Happy Button";
            happyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }else{
            happyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#37A62B")));
        }

        if ((Button)view == unsureBtn){
            Log.i("Unsure Button", "Unsure Button Clicked");
            selectedMood = "Unsure Button";
            unsureBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }else{
            unsureBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#30C697")));
        }

        if ((Button)view == sadBtn){
            Log.i("Sad Button", "Sad Button Clicked");
            selectedMood = "Sad Button";
            sadBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }else{
            sadBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#50BAC8")));
        }

        if ((Button)view == cryingBtn){
            Log.i("Crying Button", "Crying Button Clicked");
            selectedMood = "Crying Button";
            cryingBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }else{
            cryingBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4860B8")));
        }

        if ((Button)view == angryBtn){
            Log.i("Angry Button", "Angry Button Clicked");
            selectedMood = "Angry Button";
            angryBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));

        }else{
            angryBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C61313")));
        }

        if ((Button)view == backBtn){
            Log.i("Back Button", "Back Button Clicked");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        if ((Button)view == nextBtn){
            Log.i("Next Button", "Next Button Clicked");
            Intent intent = new Intent(this, AddCategoryActivity.class);
            intent.putExtra("key", selectedMood);
            startActivity(intent);
        }


    }


}