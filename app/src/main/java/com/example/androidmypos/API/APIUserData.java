package com.example.androidmypos.API;

import com.example.androidmypos.Model.ResponseModelS;
import com.example.androidmypos.Model.ResponseModelUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIUserData {
    @GET("retrieve_user.php")
    Call<ResponseModelUser> ardRetrieveData();

    @FormUrlEncoded
    @POST("in_user.php")
    Call<ResponseModelUser> ardInputData(
            @Field("username") String username,
            @Field("password") String password,
            @Field("name") String name,
            @Field("address") String address,
            @Field("level") String level
    );
    @FormUrlEncoded
    @POST("del_user.php")
    Call<ResponseModelUser> ardDeleteData
            (
              @Field("user_id") int user_id
            );
}
