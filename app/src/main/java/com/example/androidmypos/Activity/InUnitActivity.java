package com.example.androidmypos.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidmypos.API.APIUnitData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Adapter.AdapterUnit;
import com.example.androidmypos.Model.ResponseModelU;
import com.example.androidmypos.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InUnitActivity extends AppCompatActivity {

    private EditText unit;
    private Button btn_unit, btn_data;
    private ProgressBar loading;
    private String name;

    private static String URL_UNIT="http://apobase.wsjti.com/php_apobase/in_unit.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_unit);

        loading= findViewById(R.id.loading);
        unit= findViewById(R.id.unit);
        btn_unit=findViewById(R.id.btn_unit);
        btn_data=findViewById(R.id.btn_data);

        btn_unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = unit.getText().toString();
                if(name.trim().equals(""))
                {
                    unit.setError("Nama harus diisi");
                }
                else{
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

    private void addData()
    {
        APIUnitData ardData = RetroServer.konekRetrofit().create(APIUnitData.class);
        Call<ResponseModelU> inunit = ardData.ardInputData(name);//error sebelumnya karena tidak manggil responsemodelU tetapi mangil resposeModelU

        inunit.enqueue(new Callback<ResponseModelU>() {//error sebelumnya karena tidak manggil responsemodelU tetapi mangil resposeModelU
            @Override
            public void onResponse(Call<ResponseModelU> call, Response<ResponseModelU> response) {//error sebelumnya karena tidak manggil responsemodelU tetapi mangil resposeModelU
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(InUnitActivity.this, "Kode :"+kode+ "| Pesan :" +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelU> call, Throwable t) {
                Toast.makeText(InUnitActivity.this, "Gagal Menghubungkan ke Server"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

}