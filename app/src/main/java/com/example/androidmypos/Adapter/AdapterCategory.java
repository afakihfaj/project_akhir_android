package com.example.androidmypos.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.androidmypos.Activity.ReadCategoryActivity;
import com.example.androidmypos.Activity.InCategoryActivity;
import com.example.androidmypos.API.APICategoryData;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidmypos.API.RetroServer;
import com.example.androidmypos.Activity.UpCategoryActivity;
import com.example.androidmypos.Model.CategoryModel;
import com.example.androidmypos.R;
import com.example.androidmypos.Model.ResponseModelC;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.HolderData> {
    private Context ctx;
    private List<CategoryModel> listData;
    private String name;
    private int category_id;
    Button btn_update;

    public AdapterCategory(Context ctx, List<CategoryModel> listData) {
        this.ctx = ctx;
        this.listData = listData;//ndak pake this. sebelumnya listdata
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_category, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        CategoryModel dm = listData.get(position);

        holder.tvCategory_id.setText(Integer.toString(dm.getCategory_id()));//error tadi karena belum dikonversi ke string. nilai categori id masih integer harus dikonversi ke string

        holder.tvName.setText(dm.getName());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvCategory_id,tvName;
        CategoryModel dm;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvCategory_id = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih operasi yang akan dilakukan");
                    dialogPesan.setCancelable(true);

                    category_id = Integer.parseInt(tvCategory_id.getText().toString());
                    name = tvName.getText().toString();
                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            deleteData();
                            dialogInterface.dismiss();
                            ((ReadCategoryActivity) ctx).retrieveCategory();
                        }
                    });

                    dialogPesan.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Intent goInput = new Intent(ctx, UpCategoryActivity.class);
                            goInput.putExtra("kode", Integer.toString(dm.getCategory_id()));
                            goInput.putExtra("name", dm.getName());
                            Log.d("test", Integer.toString(dm.getCategory_id()));

                            ctx.startActivity(goInput);
                        }
                    });
                    dialogPesan.show();
                    return false;
                }
            });
        }

        private void deleteData(){
            APICategoryData ardData = RetroServer.konekRetrofit().create(APICategoryData.class);
            Call<ResponseModelC> hapusData = ardData.ardDeleteData(category_id);

            hapusData.enqueue(new Callback<ResponseModelC>() {
                @Override
                public void onResponse(Call<ResponseModelC> call, Response<ResponseModelC> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, "Kode : "+kode+" | Pesan :"+pesan, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseModelC> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal menghubungi server :"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
