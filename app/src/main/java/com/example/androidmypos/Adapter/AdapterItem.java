package com.example.androidmypos.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidmypos.Model.ItemModel;
import com.example.androidmypos.R;

import java.util.List;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.HolderData> {
    private Context ctx;
    private List<ItemModel> list_Item;

    public AdapterItem(Context ctx, List<ItemModel> list_Item) {
        this.ctx = ctx;
        this.list_Item = list_Item;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ItemModel dm = list_Item.get(position);

        holder.tvItem_id.setText(Integer.toString(dm.getItem_id()));

        holder.tvBarcode.setText(dm.getName());
        holder.tvName.setText(dm.getName());
        holder.tvCategory_id.setText(dm.getCategory_id());
        holder.tvUnit_id.setText(dm.getUnit_id());
        holder.tvPrice.setText(dm.getPrice());
        holder.tvStock.setText(dm.getStock());
    }

    @Override
    public int getItemCount() {
        return list_Item.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvItem_id,tvBarcode,tvName, tvCategory_id, tvUnit_id, tvPrice, tvStock;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvItem_id = itemView.findViewById(R.id.tv_id);
            tvBarcode = itemView.findViewById(R.id.tv_barcode);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCategory_id = itemView.findViewById(R.id.tv_Cid);
            tvUnit_id = itemView.findViewById(R.id.tv_Uid);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvStock = itemView.findViewById(R.id.tv_stock);

        }
    }
}
