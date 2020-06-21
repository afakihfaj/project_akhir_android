package com.example.androidmypos.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmypos.Model.UserModel;
import com.example.androidmypos.R;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.HolderData> {
    private Context ctx;
    private List<UserModel> list_User;

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
    }

    @Override
    public int getItemCount() {
        return list_User.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvUser_id,tvName, tvAddress, tvUsername;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvUser_id = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvUsername = itemView.findViewById(R.id.tv_username);
//            tvCreated = itemView.findViewById(R.id.tv_created);
//            tvUpdate = itemView.findViewById(R.id.tv_update);
        }
    }
}
