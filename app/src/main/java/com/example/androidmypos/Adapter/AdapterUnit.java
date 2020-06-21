package com.example.androidmypos.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidmypos.Model.UnitModel;
import com.example.androidmypos.R;

import java.util.List;

public class AdapterUnit extends RecyclerView.Adapter<AdapterUnit.HolderData> {
    private Context ctx;
    private List<UnitModel> list_Unit;

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
//        holder.tvCreated.setText(cm.getCreated());
//        holder.tvUpdate.setText(cm.getUpdated());
    }

    @Override
    public int getItemCount() {
        return list_Unit.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvUnit_id,tvName;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvUnit_id = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
//            tvCreated = itemView.findViewById(R.id.tv_created);
//            tvUpdate = itemView.findViewById(R.id.tv_update);
        }
    }
}
