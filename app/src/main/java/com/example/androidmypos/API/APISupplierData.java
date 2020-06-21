package com.example.androidmypos.API;

import com.example.androidmypos.Model.ResponseModelS;
import com.example.androidmypos.Model.ResponseModelU;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APISupplierData {
    @GET("retrieve_supplier.php")
    Call<ResponseModelS> ardRetrieveData();
}
