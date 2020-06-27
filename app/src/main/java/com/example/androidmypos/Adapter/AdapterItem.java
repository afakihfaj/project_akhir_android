package com.example.androidmypos.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.androidmypos.API.APIItemData;
import com.example.androidmypos.API.RetroServer;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidmypos.Activity.ReadItemActivity;
import com.example.androidmypos.Model.ResponseModelItem;
import com.example.androidmypos.Model.ItemModel;
import com.example.androidmypos.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.HolderData> {
    private Context ctx;
    private List<ItemModel> list_Item;
    private int item;
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

        holder.tvBarcode.setText(dm.getBarcode());
        holder.tvName.setText(dm.getName());
        holder.tvCategory_id.setText(dm.getCategory_id());
        holder.tvUnit_id.setText(dm.getUnit_id());
        holder.tvPrice.setText(dm.getPrice());
        holder.tvBerat.setText(dm.getBerat());
        holder.tvDeskripsi.setText(dm.getDeskripsi());

        holder.tvStock.setText(dm.getStock());
    }

    @Override
    public int getItemCount() {
        return list_Item.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvItem_id,tvBarcode,tvName, tvCategory_id, tvUnit_id, tvPrice, tvBerat, tvDeskripsi, tvStock;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvItem_id = itemView.findViewById(R.id.tv_id);
            tvBarcode = itemView.findViewById(R.id.tv_barcode);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCategory_id = itemView.findViewById(R.id.tv_Cid);
            tvUnit_id = itemView.findViewById(R.id.tv_Uid);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvBerat = itemView.findViewById(R.id.tv_berat);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            tvStock = itemView.findViewById(R.id.tv_stock);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                   android.app.AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                    dialogPesan.setMessage("Pilih operasi yang akan dilakukan");
                    dialogPesan.setCancelable(true);

                    item = Integer.parseInt(tvItem_id.getText().toString());

                    dialogPesan.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            deleteData();
                            dialogInterface.dismiss();
                            ((ReadItemActivity) ctx).retrieveItem();
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
        }
        private void deleteData(){
            APIItemData ardData = RetroServer.konekRetrofit().create(APIItemData.class);
            Call<ResponseModelItem> hapusData = ardData.ardDeleteData(item);

            hapusData.enqueue(new Callback<ResponseModelItem>() {
                @Override
                public void onResponse(Call<ResponseModelItem> call, retrofit2.Response<ResponseModelItem> response) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, "kode: "+kode+" | Pesan :"+pesan, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<ResponseModelItem> call, Throwable t) {
                    Toast.makeText(ctx, "Gagal Menghubungi Server |"+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}