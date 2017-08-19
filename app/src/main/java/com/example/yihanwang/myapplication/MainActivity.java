package com.example.yihanwang.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView topText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);



        button = (Button)findViewById(R.id.mapButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent subscription = new Intent(getApplicationContext(),MapActivity.class);
                startActivity(subscription);

            }
        });

        topText = (TextView)findViewById(R.id.topTextView);


    }
}
