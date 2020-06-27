package com.example.androidmypos.API;

import com.example.androidmypos.Model.ResponseModelS;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APISupplierData {
    @GET("retrieve_supplier.php")
    Call<ResponseModelS> ardRetrieveData();
    @FormUrlEncoded
    @POST("in_supplier.php")
    Call<ResponseModelS> ardInu(
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("description") String description
    );
    @FormUrlEncoded
    @POST("del_supplier.php")
    Call<ResponseModelS> ardDeleteData
            (
                    @Field("supplier_id") int supplier_id
            );
}
