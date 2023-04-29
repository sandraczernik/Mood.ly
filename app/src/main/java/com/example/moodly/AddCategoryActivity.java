package com.example.moodly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener {


    String key;

    CheckBox readingchk;
    CheckBox gamingchk;
    Button activityBtn;

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

    }

    @Override
    public void onClick(View view) {

        if ((Button) view == activityBtn) {
            String result = "";
            if (readingchk.isChecked()) {
                result += "\nReading";
            }
            if (gamingchk.isChecked()) {
                result += "\nGaming";
            }
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            System.out.println(result);
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str="";
        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.readingCheckbox:
                str = checked?"Reading Selected":"Reading Deselected";
                break;
            case R.id.gamingCheckbox:
                str = checked?"Gaming Selected":"Gaming Deselected";
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();


    }
}
