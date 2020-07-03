package com.example.androidmypos.API;

import com.example.androidmypos.Model.ResponseModelItem;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIItemData {
    @GET("retrieve_item.php")
    Call<ResponseModelItem> ardRetrieveData();
    @FormUrlEncoded
    @POST("in_item.php")
    Call<ResponseModelItem> ardInputData(
            @Field("barcode") String barcode,
            @Field("name") String name,
            @Field("category_id") String category_id,
            @Field("unit_id") String unit_id,
            @Field("price") String price,
            @Field("berat") String berat,
            @Field("deskripsi") String deskripsi,
            @Field("stock") String stock
    );
    @FormUrlEncoded
    @POST("del_item.php")
    Call<ResponseModelItem> ardDeleteData(
            @Field("item_id") int item_id
            );
    @FormUrlEncoded
    @POST("up_item.php")
    Call<ResponseModelItem> ardUpdateData(
            @Field("item_id") int item_id,
            @Field("barcode") String barcode,
            @Field("name") String name,
            @Field("category_id") String category_id,
            @Field("unit_id") String unit_id,
            @Field("price") String price,
            @Field("berat") String berat,
            @Field("deskripsi") String deskripsi,
            @Field("stock") String stock
    );
}
