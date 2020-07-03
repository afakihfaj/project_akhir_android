package com.example.androidmypos.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmypos.API.APIUnitData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Activity.ReadUnitActivity;
import com.example.androidmypos.Activity.UpdUnitActivity;
import com.example.androidmypos.Model.ResponseModelU;
import com.example.androidmypos.Model.UnitModel;
import com.example.androidmypos.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AdapterUnit extends RecyclerView.Adapter<AdapterUnit.HolderData> {
    private Context ctx;
    private List<UnitModel> list_Unit;
    private int uid;
    private String name;


    public AdapterUnit(Context ctx, List<UnitModel> list_Unit) {
        this.ctx = ctx;
        this.list_Unit = list_Unit;//ndak pake this. sebelumnya list_Unit
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_unit, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        UnitModel dm = list_Unit.get(position);

        holder.tvUnit_id.setText(Integer.toString(dm.getUnit_id()));//error tadi karena belum dikonversi ke string. nilai categori id masih integer harus dikonversi ke string

        holder.tvName.setText(dm.getName());
        holder.dm = dm;


//        holder.tvCreated.setText(cm.getCreated());
//        holder.tvUpdate.setText(cm.getUpdated());
    }

    @Override
    public int getItemCount() {
        return list_Unit.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvUnit_id,tvName;
        UnitModel dm;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvUnit_id = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    android.app.AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih operasi yang akan dilakukan");
                    dialogPesan.setCancelable(true);

                    uid = Integer.parseInt(tvUnit_id.getText().toString());
                    //name = tvName.getText().toString();
                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            deleteData();
                            dialogInterface.dismiss();
                            ((ReadUnitActivity) ctx).retrieveUnit();
                        }
                    });

                    dialogPesan.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Intent goInput = new Intent(ctx, UpdUnitActivity.class);
                            goInput.putExtra("unit_id",Integer.toString(dm.getUnit_id()));
                            goInput.putExtra("name", dm.getName());
                            Log.d("test", Integer.toString(dm.getUnit_id()));
                            ctx.startActivity(goInput);
                        }
                    });
                    dialogPesan.show();
                    return false;

                }
            });

        }
        private void deleteData(){
            APIUnitData ardData = RetroServer.konekRetrofit().create(APIUnitData.class);
            Call<ResponseModelU> hapusData = ardData.ardDeleteData(uid);

            hapusData.enqueue(new Callback<ResponseModelU>() {
                @Override
                public void onResponse(Call<ResponseModelU> call, retrofit2.Response<ResponseModelU> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, "kode: "+kode+" | Pesan :"+pesan, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<ResponseModelU> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server |"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
