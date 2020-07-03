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

import com.example.androidmypos.API.APIUserData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Model.ResponseModelUser;
import com.example.androidmypos.R;

import retrofit2.Call;
import retrofit2.Callback;

public class UpUserActivity extends AppCompatActivity {
    private EditText username, name, address;
    private Button btn_user, btn_data;
    private ProgressBar loading;
    public String  user_id = "";
    public String usernama = "";
    public String  nama = "";
    public String  alamat = "";

    private static String URL_USER="http://192.168.1.7/php_apobase/up_user.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_user);
        loading= findViewById(R.id.loading);
        username= findViewById(R.id.et_username);
        //password= findViewById(R.id.et_password);
        name= findViewById(R.id.et_name);
        address= findViewById(R.id.et_address);
        //level= findViewById(R.id.et_level);
        btn_user= findViewById(R.id.btn_user);
        btn_data = findViewById(R.id.btn_data);
        Intent data = getIntent();
        if(data != null)
        {

            user_id = data.getStringExtra("user_id");
            username.setText(data.getStringExtra("username"));

            name.setText(data.getStringExtra("name"));
            address.setText(data.getStringExtra("address"));
            Log.d("user", "User "+data.getStringExtra("user_id"));

        }
        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernama = username.getText().toString();
                nama = name.getText().toString();
                alamat = address.getText().toString();
                if (nama.trim().equals("")){
                    name.setError("Username harus diisi");
                }
                else if(user_id.trim().equals(""))
                {
                    username.setError("Alamat harus diisi");
                }
                else if(alamat.trim().equals(""))
                {
                    address.setError("Alamat harus diisi");
                }
                else
                {
                    upData();
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

    
    private void upData(){

        APIUserData ardData = RetroServer.konekRetrofit().create(APIUserData.class);
        Call<ResponseModelUser> updata = ardData.ardUpdateData(Integer.parseInt(user_id), usernama, nama, alamat);


        updata.enqueue(new Callback<ResponseModelUser>() {
            @Override
            public void onResponse(Call<ResponseModelUser> call, retrofit2.Response<ResponseModelUser> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(UpUserActivity.this, "kode: "+kode+" | Pesan :"+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelUser> call, Throwable t) {
                Toast.makeText(UpUserActivity.this, "Gagal Menghubungi Server |"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}