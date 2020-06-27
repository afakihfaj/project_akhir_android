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

import com.example.androidmypos.API.APIUserData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Adapter.AdapterUser;
import com.example.androidmypos.Model.ResponseModelUser;
import com.example.androidmypos.Model.UserModel;
import com.example.androidmypos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadUserActivity extends AppCompatActivity {
    private RecyclerView rvDataUser;
    private RecyclerView.Adapter adDataUser;
    private RecyclerView.LayoutManager lmDataUser;
    private ProgressBar pbDataUser;
    private SwipeRefreshLayout srlDataUser;
    private FloatingActionButton fab_tambahu;


    private List<UserModel> listUser = new ArrayList<>();


    private AdapterUser adapter;
    //    fungsi model adalah untuk menentukan data yang mau diambil dan di setting di model
    private List<UserModel> list;
    //    recycler view adalah sebuah wadah untuk menampilkna data
    private RecyclerView listuser;
    RecyclerView.LayoutManager mManagerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_user);

        rvDataUser = findViewById(R.id.rv_user);
        pbDataUser = findViewById(R.id.pb_user);
        srlDataUser = findViewById(R.id.srl_user);
        fab_tambahu = findViewById(R.id.fab_tambahu);
        lmDataUser = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDataUser.setLayoutManager(lmDataUser);

        //retrieveUser();

        srlDataUser.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlDataUser.setRefreshing(true);
                retrieveUser();
                srlDataUser.setRefreshing(false);
            }
        });


     fab_tambahu.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View v) {
              startActivity(new Intent(ReadUserActivity.this, InputUser.class));
           }
       });

    }

    public void retrieveUser(){
        pbDataUser.setVisibility(View.VISIBLE);
        APIUserData ardData = RetroServer.konekRetrofit().create(APIUserData.class);
        Call<ResponseModelUser> listDataUser = ardData.ardRetrieveData();

        listDataUser.enqueue(new Callback<ResponseModelUser>() {
            @Override
            public void onResponse(Call<ResponseModelUser> call, Response<ResponseModelUser> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

//                Toast.makeText(ReadUserActivity.this, "Kode :"+kode+ "| Pesan :" +pesan, Toast.LENGTH_SHORT).show();
                listUser = response.body().getData();

                adDataUser = new AdapterUser(ReadUserActivity.this, listUser);
                rvDataUser.setAdapter((adDataUser));
                adDataUser.notifyDataSetChanged();

                pbDataUser.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModelUser> call, Throwable t) {
                Toast.makeText(ReadUserActivity.this, "Gagal Menghubungkan ke Server", Toast.LENGTH_SHORT).show();

                pbDataUser.setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        retrieveUser();


    }
}