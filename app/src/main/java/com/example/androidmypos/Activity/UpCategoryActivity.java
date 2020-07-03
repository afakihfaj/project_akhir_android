package com.example.androidmypos.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class UpCategoryActivity extends AppCompatActivity {
    private EditText category;
    private Button btn_data, btn_update;
    private ProgressBar loading;
    public String name = "";
    public String category_id = "";

    private static String URL_CATEGORY="http://192.168.1.108/php_apobase/up_category.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_category);

        loading= findViewById(R.id.loading);
        category= findViewById(R.id.category);
        btn_data = findViewById(R.id.btn_data);
        btn_update = findViewById(R.id.btn_update);
//        category_id= findViewById(R.id.category_id);

        Intent data = getIntent();
        if(data != null)
        {
            category_id = data.getStringExtra("kode");

            category.setText(data.getStringExtra("name"));
            Log.d("category_id", "Kategori "+data.getStringExtra("kode"));
        }
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = category.getText().toString();
                if (name.trim().equals("")){
                    category.setError("Nama harus diisi");
                }
                else
                {
                    upCategory();
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

    private void upCategory(){
        APICategoryData ardData = RetroServer.konekRetrofit().create(APICategoryData.class);
        Call<ResponseModelC> ucategory = ardData.ardUpdateData(Integer.parseInt(category_id),name);

        ucategory.enqueue(new Callback<ResponseModelC>() {
            @Override
            public void onResponse(Call<ResponseModelC> call, Response<ResponseModelC> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UpCategoryActivity.this, "Kode :"+kode+ "| Pesan :" +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelC> call, Throwable t) {
                Toast.makeText(UpCategoryActivity.this, "Gagal Menghubungkan ke Server", Toast.LENGTH_SHORT).show();


            }
        });
    }
}