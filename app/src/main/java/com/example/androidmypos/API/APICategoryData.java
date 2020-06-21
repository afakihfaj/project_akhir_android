package com.example.androidmypos.API;

import com.example.androidmypos.Model.ResponseModelC;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APICategoryData {
    @GET("retrieve_category.php")
    Call<ResponseModelC> ardRetrieveData();
}
