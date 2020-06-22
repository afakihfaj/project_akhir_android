package com.example.androidmypos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.androidmypos.Activity.ReadCategoryActivity;
import com.example.androidmypos.Activity.ReadItemActivity;
import com.example.androidmypos.Activity.ReadSupplierActivity;
import com.example.androidmypos.Activity.ReadUserActivity;

public class DashboardActivity extends AppCompatActivity {
private RelativeLayout menu1;
private RelativeLayout menu2;
private RelativeLayout menu3;
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
        menu2 = (RelativeLayout) findViewById(R.id.menu2);
        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahmenu2 = new Intent(DashboardActivity.this, ReadItemActivity.class);
                startActivity(pindahmenu2);
            }
        });
        menu3 = (RelativeLayout) findViewById(R.id.menu3);
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahmenu3 = new Intent(DashboardActivity.this, ReadCategoryActivity.class);
                startActivity(pindahmenu3);
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