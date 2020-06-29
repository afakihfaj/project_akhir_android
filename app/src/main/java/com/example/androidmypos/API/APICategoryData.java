package com.example.androidmypos.API;

import com.example.androidmypos.Model.ResponseModelC;
import com.example.androidmypos.Model.ResponseModelUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APICategoryData {
    @GET("retrieve_category.php")
    Call<ResponseModelC> ardRetrieveData();
    @FormUrlEncoded
    @POST("in_category.php")
    Call<ResponseModelC> ardInputData(
            @Field("name") String name
    );
    @FormUrlEncoded
    @POST("del_category.php")
    Call<ResponseModelC> ardDeleteData(
            @Field("category_id") int category_id
    );
    @FormUrlEncoded
    @POST("up_category.php")
    Call<ResponseModelC> ardUpdateData(
            @Field("category_id") int category_id,
            @Field("name") String name
    );
}
