package com.example.moodly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity3 extends AppCompatActivity implements View.OnClickListener {

    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome3);


        registerBtn = (Button) findViewById(R.id.registerButtonWelcome);

    }

    @Override
    public void onClick(View view) {

        if ((Button)view == registerBtn){
            Log.i("RegisterButtonTest", "Register Button Clicked");
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
    }
}