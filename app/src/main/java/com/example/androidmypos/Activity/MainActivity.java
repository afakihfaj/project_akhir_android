package com.example.androidmypos.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.androidmypos.API.APIUnitData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Adapter.AdapterUnit;
import com.example.androidmypos.Model.ResponseModelU;
import com.example.androidmypos.Model.UnitModel;
import com.example.androidmypos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDataU;
    private RecyclerView.Adapter adDataU;
    private RecyclerView.LayoutManager lmDataU;
    private SwipeRefreshLayout srlDataU;
    private ProgressBar pbDataU;
    private FloatingActionButton fabUnit;
    private List<UnitModel>listUnit = new ArrayList<>();


    private AdapterUnit adapter_unit;
    //    fungsi model adalah untuk menentukan data yang mau diambil dan di setting di model
    private List<UnitModel> list_Unit;
    //    recycler view adalah sebuah wadah untuk menampilkna data
    private RecyclerView listunit;
    RecyclerView.LayoutManager mManagerUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_unit);

        rvDataU = findViewById(R.id.rv_unit);
        srlDataU = findViewById(R.id.srl_unit);
        pbDataU = findViewById(R.id.pb_unit);
//        fabUnit= findViewById(R.id.fab_unit);

        lmDataU = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDataU.setLayoutManager(lmDataU);

        retrieveUnit();

        srlDataU.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlDataU.setRefreshing(true);
                retrieveUnit();
                srlDataU.setRefreshing(false);
            }
        });
//        fabUnit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, InUnitActivity.class));
//            }
//        });
    }

    public void retrieveUnit(){
        pbDataU.setVisibility(View.VISIBLE);

        APIUnitData ardData = RetroServer.konekRetrofit().create(APIUnitData.class);
        Call<ResponseModelU> tampilDataUnit = ardData.ardRetrieveData();//error sebelumnya karena tidak manggil responsemodelU tetapi mangil resposeModelU

        tampilDataUnit.enqueue(new Callback<ResponseModelU>() {//error sebelumnya karena tidak manggil responsemodelU tetapi mangil resposeModelU
            @Override
            public void onResponse(Call<ResponseModelU> call, Response<ResponseModelU> response) {//error sebelumnya karena tidak manggil responsemodelU tetapi mangil resposeModelU
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                Toast.makeText(MainActivity.this, "Kode :"+kode+ "| Pesan :" +pesan, Toast.LENGTH_SHORT).show();
                listUnit = response.body().getData();

                adDataU = new AdapterUnit(MainActivity.this, listUnit);
                rvDataU.setAdapter((adDataU));
                adDataU.notifyDataSetChanged();

                pbDataU.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModelU> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungkan ke Server", Toast.LENGTH_SHORT).show();

                pbDataU.setVisibility(View.INVISIBLE);
            }
        });
    }

}
