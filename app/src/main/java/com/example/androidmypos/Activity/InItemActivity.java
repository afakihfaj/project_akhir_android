package com.example.androidmypos.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidmypos.API.APIItemData;

import com.example.androidmypos.API.RetroServer;

import com.example.androidmypos.Adapter.AdapterItem;
import com.example.androidmypos.Model.ResponseModelItem;
import com.example.androidmypos.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InItemActivity extends AppCompatActivity {
    private EditText barcode, name, category_id, unit_id, price, berat, deskripsi, stock;
    private Button btn_item, btn_data;
    private ProgressBar loading;
    private String Barcode, Name, Category_id, Unit_id, Price, Berat, Deskripsi, Stock;

    private static String URL_ITEM="http://apobase.wsjti.com/php_apobase/in_category.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_item);

        barcode = findViewById(R.id.barcode);
        name = findViewById(R.id.name);
        category_id = findViewById(R.id.category_id);
        unit_id = findViewById(R.id.unit_id);
        price = findViewById(R.id.price);
        berat = findViewById(R.id.berat);
        deskripsi = findViewById(R.id.deskripsi);
        stock = findViewById(R.id.stock);
        btn_item = findViewById(R.id.btn_item);
        btn_data =findViewById(R.id.btn_data);

        btn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Barcode = barcode.getText().toString();
            Name = name.getText().toString();
            Category_id = category_id.getText().toString();
            Unit_id = unit_id.getText().toString();
            Price = price.getText().toString();
            Berat = berat.getText().toString();
            Deskripsi = deskripsi.getText().toString();
            Stock = stock.getText().toString();

            if(Barcode.trim().equals(""))
            {
                barcode.setError("barcode harus diisi");
            }
            else if(Name.trim().equals(""))
            {
                name.setError("nama harus diisi");
            }
            else if(Category_id.trim().equals(""))
            {
                category_id.setError("category harus diisi");
            }
            else if(Unit_id.trim().equals(""))
            {
                unit_id.setError("unit harus diisi");
            }
            else if(Price.trim().equals(""))
            {
                price.setError("price harus diisi");
            }
            else if(Berat.trim().equals(""))
            {
                berat.setError("berat harus diisi");
            }
            else if(Deskripsi.trim().equals(""))
            {
                deskripsi.setError("deskripsi harus diisi");
            }
            else if(Stock.trim().equals(""))
            {
                stock.setError("stock harus diisi");
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

    private void addData()
    {

        APIItemData ardData = RetroServer.konekRetrofit().create(APIItemData.class);
        Call<ResponseModelItem> inputData = ardData.ardInputData(Barcode, Name, Category_id, Unit_id, Price, Berat, Deskripsi, Stock);

        inputData.enqueue(new Callback<ResponseModelItem>() {
            @Override
            public void onResponse(Call<ResponseModelItem> call, Response<ResponseModelItem> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(InItemActivity.this, "kode: "+kode+" | Pesan :"+pesan, Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<ResponseModelItem> call, Throwable t) {
                Toast.makeText(InItemActivity.this, "Gagal Menghubungkan ke Server"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }
}