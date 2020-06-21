package com.example.androidmypos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReadUnitActivity extends AppCompatActivity {
    private Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_unit);

//        btn_add = findViewById(R.id.btn_unit);
//
//
//        btn_add.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intentinUnitactivity = new Intent(ReadUnitActivity.this, InUnitActivity.class);
//                        startActivity(intentinUnitactivity);
//                        finish();
//                    }
//                });
    }
}
