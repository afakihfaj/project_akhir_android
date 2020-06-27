package com.example.androidmypos.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidmypos.API.APISupplierData;
import com.example.androidmypos.Model.ResponseModelS;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmypos.API.APIItemData;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Activity.ReadSupplierActivity;

import com.example.androidmypos.Model.SupplierModel;

import com.example.androidmypos.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AdapterSupplier extends RecyclerView.Adapter<AdapterSupplier.HolderData> {
    private Context ctx;
    private List<SupplierModel> list_Supplier;
    private int sup_id;

    public AdapterSupplier(Context ctx, List<SupplierModel> list_Supplier) {
        this.ctx = ctx;
        this.list_Supplier = list_Supplier;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_supplier, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        SupplierModel dm = list_Supplier.get(position);

        holder.tvSupplier_id.setText(Integer.toString(dm.getSupplier_id()));//error tadi karena belum dikonversi ke string. nilai categori id masih integer harus dikonversi ke string

        holder.tvName.setText(dm.getName());
        holder.tvPhone.setText(dm.getPhone());
        holder.tvAddress.setText(dm.getAddress());
        holder.tvDescription.setText(dm.getDescription());
    }

    @Override
    public int getItemCount() {
        return list_Supplier.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvSupplier_id,tvName, tvPhone, tvAddress, tvDescription;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvSupplier_id = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvDescription = itemView.findViewById(R.id.tv_description);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    android.app.AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih operasi yang akan dilakukan");
                    dialogPesan.setCancelable(true);

                    sup_id = Integer.parseInt(tvSupplier_id.getText().toString());

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            deleteData();
                            dialogInterface.dismiss();
                            ((ReadSupplierActivity) ctx).retrieveSupplier();
                        }
                    });

                    dialogPesan.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                        }
                    });
                    dialogPesan.show();
                    return false;
                }
            });
//            tvCreated = itemView.findViewById(R.id.tv_created);
//            tvUpdate = itemView.findViewById(R.id.tv_update);
        }
        private void deleteData(){
            APISupplierData ardData = RetroServer.konekRetrofit().create(APISupplierData.class);
            Call<ResponseModelS> hapusData = ardData.ardDeleteData(sup_id);

            hapusData.enqueue(new Callback<ResponseModelS>() {
                @Override
                public void onResponse(Call<ResponseModelS> call, retrofit2.Response<ResponseModelS> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, "kode: "+kode+" | Pesan :"+pesan, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<ResponseModelS> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server |"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
