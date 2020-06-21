package com.example.androidmypos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView name, username;
    private Button btn_logout, btn_dashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        btn_dashboard = findViewById(R.id.btn_dashboard);
        btn_logout = findViewById(R.id.btn_logout);

        final Intent intent = getIntent();
        String extraName = intent.getStringExtra("name");
        String extraUsername = intent.getStringExtra("username");

        name.setText(extraName);
        username.setText(extraUsername);
        btn_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdashboard = new Intent(HomeActivity.this, DashboardActivity.class);
                startActivity(intentdashboard);

            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}