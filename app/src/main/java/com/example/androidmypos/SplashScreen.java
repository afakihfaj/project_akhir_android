package com.example.androidmypos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SystemClock.sleep(3000);
        Intent loginintent = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(loginintent);
        finish();

    }
}