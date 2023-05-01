package com.example.moodly;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity2 extends AppCompatActivity implements View.OnClickListener{

    Button welcomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome2);
        //XML to variable
        welcomeBtn = (Button) findViewById(R.id.welcomeButtonNext2);
    }

    @Override
    //When button is pressed take user to next activity
    public void onClick(View view) {
        if ((Button)view == welcomeBtn){
            Log.i("WelcomeButton", "Welcome Button Clicked");
            finish();
            Intent intent = new Intent(this, WelcomeActivity3.class);
            startActivity(intent);
        }
    }
}