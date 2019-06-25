package com.example.pooja.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(splashscreen.this, Main7Activity.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }
}
