package com.example.yihanwang.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ImageInformationActivity extends AppCompatActivity {
    private String plantNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_information);
        if (getIntent().hasExtra("PLANT1")) {
            TextView tv = (TextView) findViewById(R.id.plantText);
            String text = getIntent().getExtras().getString("PLANT1");
            tv.setText(text);
        }
        if (getIntent().hasExtra("PLANT2")) {
            TextView tv = (TextView) findViewById(R.id.plantText);
            String text = getIntent().getExtras().getString("PLANT2");
            tv.setText(text);
        }
        if (getIntent().hasExtra("PLANT3")) {
            TextView tv = (TextView) findViewById(R.id.plantText);
            String text = getIntent().getExtras().getString("PLANT3");
            tv.setText(text);
        }
        if (getIntent().hasExtra("PLANT4")) {
            TextView tv = (TextView) findViewById(R.id.plantText);
            String text = getIntent().getExtras().getString("PLANT4");
            tv.setText(text);
        }



    }
}
