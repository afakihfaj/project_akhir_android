package com.example.androidmypos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.androidmypos.Activity.ReadUserActivity;

public class DashboardActivity extends AppCompatActivity {
private RelativeLayout menu1;
private RelativeLayout menu4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        menu1 = (RelativeLayout) findViewById(R.id.menu1);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahmenu1 = new Intent(DashboardActivity.this, ReadUserActivity.class);
                startActivity(pindahmenu1);

            }


        });

        menu4 = (RelativeLayout) findViewById(R.id.menu4);
        menu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahmenu4 = new Intent(DashboardActivity.this, ReadUnitActivity.class);
                startActivity(pindahmenu4);
            }
        });

    }


}