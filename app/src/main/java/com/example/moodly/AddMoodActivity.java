package com.example.moodly;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddMoodActivity extends AppCompatActivity implements View.OnClickListener{
    //Buttons
    Button veryHappyBtn;
    Button happyBtn;
    Button unsureBtn;
    Button sadBtn;
    Button cryingBtn;
    Button angryBtn;

    //Navigation buttons
    Button backBtn;
    Button nextBtn;

    //Validation
    String selectedMood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mood);

        //Assigning XML elements to variables
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

        //If statements for each button, stores string of button as well as changes colour for visibility
        if ((Button)view == veryHappyBtn){
            Log.i("Very Happy Button", "Very Happy Button Clicked");
            selectedMood =  "Very Happy";
            veryHappyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }
        else{
            veryHappyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#105E14")));
        }
        if ((Button)view == happyBtn){
            Log.i("Happy Button", "Happy Button Clicked");
            selectedMood = "Happy";
            happyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }
        else{
            happyBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#37A62B")));
        }
        if ((Button)view == unsureBtn){
            Log.i("Unsure Button", "Unsure Button Clicked");
            selectedMood = "Unsure";
            unsureBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }
        else{
            unsureBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#30C697")));
        }
        if ((Button)view == sadBtn){
            Log.i("Sad Button", "Sad Button Clicked");
            selectedMood = "Sad";
            sadBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }
        else{
            sadBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#50BAC8")));
        }
        if ((Button)view == cryingBtn){
            Log.i("Crying Button", "Crying Button Clicked");
            selectedMood = "Crying";
            cryingBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }
        else{
            cryingBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4860B8")));
        }
        if ((Button)view == angryBtn){
            Log.i("Angry Button", "Angry Button Clicked");
            selectedMood = "Angry";
            angryBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
        }
        else{
            angryBtn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#C61313")));
        }

        //Navigational buttons
        if ((Button)view == backBtn){
            Log.i("Back Button", "Back Button Clicked");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if ((Button)view == nextBtn) {
            if (selectedMood != "") {
                Log.i("Next Button", "Next Button Clicked");
                //Passing intent to category activity
                Intent intent = new Intent(this, AddCategoryActivity.class);
                intent.putExtra("key", selectedMood);
                startActivity(intent);
                //Mood selection is empty
            } else {
                Toast.makeText(getApplicationContext(), "Please select a mood", Toast.LENGTH_SHORT).show();
            }
        }
    }
}