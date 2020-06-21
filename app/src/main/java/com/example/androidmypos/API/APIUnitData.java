package com.example.androidmypos.API;

import com.example.androidmypos.Model.ResponseModelC;
import com.example.androidmypos.Model.ResponseModelU;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIUnitData {
    @GET("retrieve_unit.php")
    Call<ResponseModelU> ardRetrieveData();//sebelumnya responsemodel C diubah ke responsemodelU
}
