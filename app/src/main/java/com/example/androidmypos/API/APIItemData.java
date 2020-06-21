package com.example.androidmypos.API;

import com.example.androidmypos.Model.ResponseModelItem;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIItemData {
    @GET("retrieve_item.php")
    Call<ResponseModelItem> ardRetrieveData();
}
