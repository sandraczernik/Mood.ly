package com.example.moodly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity3 extends AppCompatActivity implements View.OnClickListener {
    //Navigation
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome3);

        //XML to variable
        registerBtn = (Button) findViewById(R.id.registerButtonWelcome);
    }

    @Override
    //When button is pressed take user to main activity
    public void onClick(View view) {
        if ((Button)view == registerBtn){
            Log.i("RegisterButtonTest", "Register Button Clicked");
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}