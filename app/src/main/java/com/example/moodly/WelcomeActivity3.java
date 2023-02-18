package com.example.moodly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity3 extends AppCompatActivity implements View.OnClickListener {

    Button welcomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome3);

        welcomeBtn = (Button) findViewById(R.id.welcomeButtonNext3);

    }

    @Override
    public void onClick(View view) {
        if ((Button)view == welcomeBtn){
            Log.i("WelcomeButton", "Welcome Button Clicked");
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
    }
}