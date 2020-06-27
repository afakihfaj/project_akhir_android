package com.example.androidmypos.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidmypos.API.APISupplierData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Adapter.AdapterSupplier;
import com.example.androidmypos.Model.ResponseModelS;
import com.example.androidmypos.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InSupplierActivity extends AppCompatActivity {

    private EditText  supplier, phone, address, description;
    private Button btn_supplier,btn_data;
    private ProgressBar loading;
    private String name, hp, alamat, desc;

    private static String URL_SUPPLIER="http://192.168.1.6/php_apobase/in_supplier.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_supplier);

        loading= findViewById(R.id.loading);
        supplier= findViewById(R.id.supplier);
        phone= findViewById(R.id.phone);
        address= findViewById(R.id.address);
        description= findViewById(R.id.description);
        btn_supplier=findViewById(R.id.btn_supplier);
        btn_data=findViewById(R.id.btn_data);

        btn_supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = supplier.getText().toString();
                hp = phone.getText().toString();
                alamat = address.getText().toString();
                desc = description.getText().toString();

                if (name.trim().equals(""))
                {
                    supplier.setError("Nama Supplier harus diisi");
                }
                else if (hp.trim().equals(""))
                {
                    phone.setError("No HP harus diisi");
                }
                else if (alamat.trim().equals(""))
                {
                    address.setError("Alamat harus diisi");
                }
                else if (desc.trim().equals(""))
                {
                    description.setError("Deskripsi Supplier harus diisi");
                }
                else{
                    addSupplier();
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
    private void addSupplier(){
        APISupplierData ardData = RetroServer.konekRetrofit().create(APISupplierData.class);
        Call<ResponseModelS> inputdata = ardData.ardInu(name, hp, alamat, desc);

        inputdata.enqueue(new Callback<ResponseModelS>() {
            @Override
            public void onResponse(Call<ResponseModelS> call, Response<ResponseModelS> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(InSupplierActivity.this, "Kode :"+kode+ "| Pesan :" +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModelS> call, Throwable t) {
                Toast.makeText(InSupplierActivity.this, "Gagal Menghubungkan ke Server"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

}