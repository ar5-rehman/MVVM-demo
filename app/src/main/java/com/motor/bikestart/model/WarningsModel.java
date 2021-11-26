package com.motor.bikestart.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WarningsModel {

    @SerializedName("battery")
    @Expose
    private List<Integer> battery = null;
    @SerializedName("tempreture")
    @Expose
    private List<Integer> tempreture = null;

    public List<Integer> getBattery() {
        return battery;
    }

    public void setBattery(List<Integer> battery) {
        this.battery = battery;
    }

    public List<Integer> getTempreture() {
        return tempreture;
    }

    public void setTempreture(List<Integer> tempreture) {
        this.tempreture = tempreture;
    }
}
