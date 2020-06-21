package com.example.androidmypos.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.androidmypos.API.APIItemData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Adapter.AdapterItem;
import com.example.androidmypos.Model.ItemModel;
import com.example.androidmypos.Model.ResponseModelItem;
import com.example.androidmypos.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadItemActivity extends AppCompatActivity {
    private RecyclerView rvDataI;
    private RecyclerView.Adapter adDataI;
    private RecyclerView.LayoutManager lmDataI;
    private ProgressBar pbDataI;
    private SwipeRefreshLayout srlDataI;

    private List<ItemModel> listItem = new ArrayList<>();


    private AdapterItem adapter;
    //    fungsi model adalah untuk menentukan data yang mau diambil dan di setting di model
    private List<ItemModel> list;
    //    recycler view adalah sebuah wadah untuk menampilkna data
    private RecyclerView listitem;
    RecyclerView.LayoutManager mManagerItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_item);

        rvDataI = findViewById(R.id.rv_item);
        pbDataI = findViewById(R.id.pb_item);
        srlDataI = findViewById(R.id.srl_item);
        lmDataI = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDataI.setLayoutManager(lmDataI);

        retrieveItem();

        srlDataI.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlDataI.setRefreshing(true);
                retrieveItem();
                srlDataI.setRefreshing(false);
            }
        });
    }

    public void retrieveItem(){
        pbDataI.setVisibility(View.VISIBLE);
        APIItemData ardData = RetroServer.konekRetrofit().create(APIItemData.class);
        Call<ResponseModelItem> listDataItem = ardData.ardRetrieveData();

        listDataItem.enqueue(new Callback<ResponseModelItem>() {
            @Override
            public void onResponse(Call<ResponseModelItem> call, Response<ResponseModelItem> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(ReadItemActivity.this, "Kode :"+kode+ "| Pesan :" +pesan, Toast.LENGTH_SHORT).show();
                listItem = response.body().getData();

                adDataI = new AdapterItem(ReadItemActivity.this, listItem);
                rvDataI.setAdapter((adDataI));
                adDataI.notifyDataSetChanged();

                pbDataI.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModelItem> call, Throwable t) {
                Toast.makeText(ReadItemActivity.this, "Gagal Menghubungkan ke Server", Toast.LENGTH_SHORT).show();

                pbDataI.setVisibility(View.INVISIBLE);
            }
        });
    }

}
