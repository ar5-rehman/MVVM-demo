package com.motor.bikestart.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.motor.bikestart.model.DataModel;
import com.motor.bikestart.network.ApiService;
import com.motor.bikestart.network.RetroInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryClass {

    ApiService apiService;

    public RepositoryClass(){
        apiService = RetroInstance.getInstance().create(ApiService.class);
    }

    public MutableLiveData<DataModel> getBikeData() {
        final MutableLiveData<DataModel> data = new MutableLiveData<>();
        apiService.getBikeData()
                .enqueue(new Callback<DataModel>() {


                    @Override
                    public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<DataModel> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
