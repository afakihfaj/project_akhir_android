package com.example.androidmypos.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

public class InputUser extends AppCompatActivity {
    private EditText username, password, name, address, level;
    private Button btn_user, btn_data;
    private ProgressBar loading;
    private String uname, pword, nama, alamat, lepel;

    private static String URL_USER="http://192.168.1.8/php_apobase/in_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_user);

        loading= findViewById(R.id.loading);
        username= findViewById(R.id.et_username);
        password= findViewById(R.id.et_password);
        name= findViewById(R.id.et_name);
        address= findViewById(R.id.et_address);
        level= findViewById(R.id.et_level);
        btn_user= findViewById(R.id.btn_user);
        btn_data = findViewById(R.id.btn_data);


        btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uname = username.getText().toString();
                pword = password.getText().toString();
                nama = name.getText().toString();
                alamat = address.getText().toString();
                lepel = level.getText().toString();

                if(uname.trim().equals("")){
                    username.setError("Username Harus Diisi");
                }
                else if (pword.trim().equals("")){
                    password.setError("Password Harus Diisi");
                }
                else if (nama.trim().equals("")){
                    name.setError("Nama Harus Diisi");
                }
                else if (alamat.trim().equals("")){
                    address.setError("Alamat Harus Diisi");
                }
                else if (lepel.trim().equals("")){
                    level.setError("Level Harus Diisi");
                }
                else
                {
                    addData();
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

    private void addData(){
        APIUserData ardData = RetroServer.konekRetrofit().create(APIUserData.class);
        Call<ResponseModelUser> inputData = ardData.ardInputData(uname, pword, nama, alamat, lepel);

        inputData.enqueue(new Callback<ResponseModelUser>() {
            @Override
            public void onResponse(Call<ResponseModelUser> call, retrofit2.Response<ResponseModelUser> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(InputUser.this, "kode: "+kode+" | Pesan :"+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelUser> call, Throwable t) {
                Toast.makeText(InputUser.this, "Gagal Menghubungi Server |"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}