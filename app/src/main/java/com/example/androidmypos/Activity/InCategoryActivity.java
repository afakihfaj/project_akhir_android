package com.example.androidmypos.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidmypos.API.APICategoryData;

import com.example.androidmypos.API.RetroServer;

import com.example.androidmypos.Model.ResponseModelC;

import com.example.androidmypos.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InCategoryActivity extends AppCompatActivity {

    private EditText category;
    private Button btn_category, btn_data;
    private ProgressBar loading;
    private String name;

    private static String URL_CATEGORY="http://192.168.1.6/php_apobase/in_category.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_category);

        loading= findViewById(R.id.loading);
        category= findViewById(R.id.category);
        btn_category=findViewById(R.id.btn_category);
        btn_data = findViewById(R.id.btn_data);

        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = category.getText().toString();
                if (name.trim().equals("")){
                    category.setError("Nama harus diisi");
                }
                else
                {
                    inputCategory();
                }
            }
        });
        btn_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inputCategory(){
        APICategoryData ardData = RetroServer.konekRetrofit().create(APICategoryData.class);
        Call<ResponseModelC> icategory = ardData.ardInputData(name);

        icategory.enqueue(new Callback<ResponseModelC>() {
            @Override
            public void onResponse(Call<ResponseModelC> call, Response<ResponseModelC> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(InCategoryActivity.this, "Kode :"+kode+ "| Pesan :" +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelC> call, Throwable t) {
                Toast.makeText(InCategoryActivity.this, "Gagal Menghubungkan ke Server", Toast.LENGTH_SHORT).show();


            }
        });
    }

}