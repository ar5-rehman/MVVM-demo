package com.motor.bikestart.network;

import com.motor.bikestart.model.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("dummy-data")
    Call<DataModel> getBikeData();
}
