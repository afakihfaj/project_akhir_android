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

import com.example.androidmypos.API.APIUnitData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Model.ResponseModelU;
import com.example.androidmypos.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdUnitActivity extends AppCompatActivity {

    private EditText unit;
    private Button btn_unit, btn_data;
    private ProgressBar loading;
    public String nama = "";
    public String unitt = "";

    private static String URL_UNIT="http://192.168.1.7/php_apobase/up_unit.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_unit);

        loading= findViewById(R.id.loading);
        unit= findViewById(R.id.unit);
        btn_unit=findViewById(R.id.btn_unit);
        btn_data=findViewById(R.id.btn_data);
        Intent data = getIntent();

        if(data != null)
        {
            unitt = data.getStringExtra("unit_id");
            unit.setText(data.getStringExtra("name"));
            Log.d("unitt", "Unit "+data.getStringExtra("unit_id"));



        }
        btn_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama = unit.getText().toString();
                if (nama.trim().equals("")){
                    unit.setError("Nama harus diisi");
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

    private void upData()
    {
        APIUnitData ardData = RetroServer.konekRetrofit().create(APIUnitData.class);
        Call<ResponseModelU> upnunit = ardData.ardUpdateData(Integer.parseInt(unitt),nama);//error sebelumnya karena tidak manggil responsemodelU tetapi mangil resposeModelU

        upnunit.enqueue(new Callback<ResponseModelU>() {//error sebelumnya karena tidak manggil responsemodelU tetapi mangil resposeModelU
            @Override
            public void onResponse(Call<ResponseModelU> call, Response<ResponseModelU> response) {//error sebelumnya karena tidak manggil responsemodelU tetapi mangil resposeModelU
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(UpdUnitActivity.this, "Kode :"+kode+ "| Pesan :" +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelU> call, Throwable t) {
                Toast.makeText(UpdUnitActivity.this, "Gagal Menghubungkan ke Server"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}