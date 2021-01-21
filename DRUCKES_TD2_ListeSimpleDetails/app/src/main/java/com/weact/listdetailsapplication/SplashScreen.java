package com.weact.listdetailsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent main = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }
}