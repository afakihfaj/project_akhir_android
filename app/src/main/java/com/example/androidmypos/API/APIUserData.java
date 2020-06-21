package com.example.androidmypos.API;

import com.example.androidmypos.Model.ResponseModelS;
import com.example.androidmypos.Model.ResponseModelUser;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIUserData {
    @GET("retrieve_user.php")
    Call<ResponseModelUser> ardRetrieveData();
}
