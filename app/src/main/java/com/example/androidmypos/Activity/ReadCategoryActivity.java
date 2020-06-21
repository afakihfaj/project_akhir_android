package com.example.androidmypos.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidmypos.API.APICategoryData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Adapter.AdapterCategory;
import com.example.androidmypos.InCategoryActivity;
import com.example.androidmypos.InUnitActivity;
import com.example.androidmypos.Model.CategoryModel;
import com.example.androidmypos.Model.ResponseModelC;
import com.example.androidmypos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadCategoryActivity extends AppCompatActivity {
    private RecyclerView rvDataC;
    private RecyclerView.Adapter adDataC;
    private RecyclerView.LayoutManager lmDataC;
    private ProgressBar pbDataC;
    private SwipeRefreshLayout srlDataC;
    private FloatingActionButton fabCategory;

    private List<CategoryModel> listCategory = new ArrayList<>();


    private AdapterCategory adapter;
    //    fungsi model adalah untuk menentukan data yang mau diambil dan di setting di model
    private List<CategoryModel> list;
    //    recycler view adalah sebuah wadah untuk menampilkna data
    private RecyclerView listcategory;
    RecyclerView.LayoutManager mManagerCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_category);

        rvDataC = findViewById(R.id.rv_category);
        pbDataC = findViewById(R.id.pb_category);
        srlDataC = findViewById(R.id.srl_category);
        fabCategory = findViewById(R.id.fab_category);
        lmDataC = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDataC.setLayoutManager(lmDataC);

        retrieveCategory();

        srlDataC.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlDataC.setRefreshing(true);
                retrieveCategory();
                srlDataC.setRefreshing(false);
            }
        });

        fabCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReadCategoryActivity.this, InCategoryActivity.class));
            }
        });

    }

    public void retrieveCategory(){
        pbDataC.setVisibility(View.VISIBLE);
        APICategoryData ardData = RetroServer.konekRetrofit().create(APICategoryData.class);
        Call<ResponseModelC> tampilDataCategory = ardData.ardRetrieveData();

        tampilDataCategory.enqueue(new Callback<ResponseModelC>() {
            @Override
            public void onResponse(Call<ResponseModelC> call, Response<ResponseModelC> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(ReadCategoryActivity.this, "Kode :"+kode+ "| Pesan :" +pesan, Toast.LENGTH_SHORT).show();
                listCategory = response.body().getData();

                adDataC = new AdapterCategory(ReadCategoryActivity.this, listCategory);
                rvDataC.setAdapter((adDataC));
                adDataC.notifyDataSetChanged();

                pbDataC.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModelC> call, Throwable t) {
                Toast.makeText(ReadCategoryActivity.this, "Gagal Menghubungkan ke Server", Toast.LENGTH_SHORT).show();

                pbDataC.setVisibility(View.INVISIBLE);
            }
        });
    }

}
