package com.example.androidmypos.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmypos.Model.SupplierModel;
import com.example.androidmypos.Model.UnitModel;
import com.example.androidmypos.R;

import java.util.List;

public class AdapterSupplier extends RecyclerView.Adapter<AdapterSupplier.HolderData> {
    private Context ctx;
    private List<SupplierModel> list_Supplier;

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
//            tvCreated = itemView.findViewById(R.id.tv_created);
//            tvUpdate = itemView.findViewById(R.id.tv_update);
        }
    }
}
