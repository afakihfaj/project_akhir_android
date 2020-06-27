package com.example.androidmypos.API;


import com.example.androidmypos.Model.ResponseModelU;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIUnitData {
    @GET("retrieve_unit.php")
    Call<ResponseModelU> ardRetrieveData();//sebelumnya responsemodel C diubah ke responsemodelU
    @FormUrlEncoded
    @POST("in_unit.php")
    Call<ResponseModelU> ardInputData(
            @Field("name") String name
    );
    @FormUrlEncoded
    @POST("del_unit.php")
    Call<ResponseModelU> ardDeleteData(
            @Field("unit_id") int unit_id
    );
}
