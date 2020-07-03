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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.androidmypos.API.APIUserData;
import com.example.androidmypos.API.RetroServer;


import com.example.androidmypos.Activity.InCategoryActivity;
import com.example.androidmypos.Activity.InputUser;
import com.example.androidmypos.Activity.ReadUserActivity;
import com.example.androidmypos.Activity.UpUserActivity;
import com.example.androidmypos.Model.ResponseModelUser;
import com.example.androidmypos.Model.UserModel;
import com.example.androidmypos.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.HolderData> {
    private Context ctx;
    private List<UserModel> list_User;
    private int user_id, level;
    private SwipeRefreshLayout srlDataUser;
    public AdapterUser(Context ctx, List<UserModel> list_User) {
        this.ctx = ctx;
        this.list_User = list_User;

    }


    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_user, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        UserModel dm = list_User.get(position);

        holder.tvUser_id.setText(Integer.toString(dm.getUser_id()));//error tadi karena belum dikonversi ke string. nilai categori id masih integer harus dikonversi ke string

        holder.tvName.setText(dm.getName());
        holder.tvAddress.setText(dm.getAddress());
        holder.tvUsername.setText(dm.getUsername());
        holder.dm = dm;

    }

    @Override
    public int getItemCount() {
        return list_User.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvUser_id,tvName, tvAddress, tvUsername;
        UserModel dm;

        public HolderData(@NonNull View itemView) {

            super(itemView);
            tvUser_id = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvUsername = itemView.findViewById(R.id.tv_username);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    android.app.AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih operasi yang akan dilakukan");
                    dialogPesan.setCancelable(true);

                    user_id = Integer.parseInt(tvUser_id.getText().toString());


                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            deleteData();
                            dialogInterface.dismiss();
                            ((ReadUserActivity) ctx).retrieveUser();
                        }
                    });

                    dialogPesan.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Intent goInput = new Intent(ctx, UpUserActivity.class);
                            goInput.putExtra("user_id", Integer.toString(dm.getUser_id()));
                            goInput.putExtra("username", dm.getUsername());
                            goInput.putExtra("name", dm.getName());
                            goInput.putExtra("address", dm.getAddress());
                            Log.d("test", Integer.toString(dm.getUser_id()));

                            ctx.startActivity(goInput);
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
            APIUserData ardData = RetroServer.konekRetrofit().create(APIUserData.class);
            Call<ResponseModelUser> hapusData = ardData.ardDeleteData(user_id);

            hapusData.enqueue(new Callback<ResponseModelUser>() {
                @Override
                public void onResponse(Call<ResponseModelUser> call, retrofit2.Response<ResponseModelUser> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, "kode: "+kode+" | Pesan :"+pesan, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<ResponseModelUser> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server |"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }




}
