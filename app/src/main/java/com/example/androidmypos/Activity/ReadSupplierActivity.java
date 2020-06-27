package com.example.androidmypos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.androidmypos.API.APISupplierData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Adapter.AdapterSupplier;
import com.example.androidmypos.Model.ResponseModelS;
import com.example.androidmypos.Model.SupplierModel;
import com.example.androidmypos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadSupplierActivity extends AppCompatActivity {
    private RecyclerView rvDataC;
    private RecyclerView.Adapter adDataC;
    private RecyclerView.LayoutManager lmDataC;
    private ProgressBar pbDataC;
    private SwipeRefreshLayout srlDataC;
    private FloatingActionButton fabSupplier;

    private List<SupplierModel> listSupplier = new ArrayList<>();


    private AdapterSupplier adapter;
    //    fungsi model adalah untuk menentukan data yang mau diambil dan di setting di model
    private List<SupplierModel> list;
    //    recycler view adalah sebuah wadah untuk menampilkna data
    private RecyclerView listsupplier;
    RecyclerView.LayoutManager mManagerSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_supplier);

        rvDataC = findViewById(R.id.rv_supplier);
        pbDataC = findViewById(R.id.pb_supplier);
        srlDataC = findViewById(R.id.srl_supplier);
        fabSupplier = findViewById(R.id.fab_supplier);
        lmDataC = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDataC.setLayoutManager(lmDataC);

        //retrieveSupplier();

        srlDataC.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlDataC.setRefreshing(true);
                retrieveSupplier();
                srlDataC.setRefreshing(false);
            }
        });

        fabSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReadSupplierActivity.this, InSupplierActivity.class));
            }
        });

    }

    public void retrieveSupplier(){
        pbDataC.setVisibility(View.VISIBLE);
        APISupplierData ardData = RetroServer.konekRetrofit().create(APISupplierData.class);
        Call<ResponseModelS> listDataSupplier = ardData.ardRetrieveData();

        listDataSupplier.enqueue(new Callback<ResponseModelS>() {
            @Override
            public void onResponse(Call<ResponseModelS> call, Response<ResponseModelS> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

               // Toast.makeText(ReadSupplierActivity.this, "Kode :"+kode+ "| Pesan :" +pesan, Toast.LENGTH_SHORT).show();
                listSupplier = response.body().getData();

                adDataC = new AdapterSupplier(ReadSupplierActivity.this, listSupplier);
                rvDataC.setAdapter((adDataC));
                adDataC.notifyDataSetChanged();

                pbDataC.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModelS> call, Throwable t) {
                Toast.makeText(ReadSupplierActivity.this, "Gagal Menghubungkan ke Server", Toast.LENGTH_SHORT).show();

                pbDataC.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveSupplier();
    }
}
