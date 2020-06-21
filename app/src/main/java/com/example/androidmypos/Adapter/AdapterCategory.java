package com.example.androidmypos.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmypos.Model.CategoryModel;
import com.example.androidmypos.R;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.HolderData> {
    private Context ctx;
    private List<CategoryModel> listData;

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
//        holder.tvCreated.setText(cm.getCreated());
//        holder.tvUpdate.setText(cm.getUpdated());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvCategory_id,tvName;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvCategory_id = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
//            tvCreated = itemView.findViewById(R.id.tv_created);
//            tvUpdate = itemView.findViewById(R.id.tv_update);
        }
    }
}
