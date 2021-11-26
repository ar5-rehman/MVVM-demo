package com.motor.bikestart.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.motor.bikestart.model.DataModel;
import com.motor.bikestart.repository.RepositoryClass;

public class ViewModelClass extends ViewModel {

    private MutableLiveData<DataModel> bikeData;
    private RepositoryClass repo;

    public ViewModelClass(){
        repo = new RepositoryClass();
        bikeData = repo.getBikeData();
    }

    public MutableLiveData<DataModel> getBikeData(){
        return bikeData;
    }

}
