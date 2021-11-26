package com.motor.bikestart.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("battery")
    @Expose
    private List<Integer> battery = null;
    @SerializedName("speed")
    @Expose
    private List<Integer> speed = null;
    @SerializedName("range")
    @Expose
    private List<Integer> range = null;
    @SerializedName("rpm")
    @Expose
    private List<Integer> rpm = null;
    @SerializedName("tilt_angle")
    @Expose
    private List<Integer> tiltAngle = null;
    @SerializedName("warnings")
    @Expose
    private WarningsModel warnings;

    public List<Integer> getBattery() {
        return battery;
    }

    public void setBattery(List<Integer> battery) {
        this.battery = battery;
    }

    public List<Integer> getSpeed() {
        return speed;
    }

    public void setSpeed(List<Integer> speed) {
        this.speed = speed;
    }

    public List<Integer> getRange() {
        return range;
    }

    public void setRange(List<Integer> range) {
        this.range = range;
    }

    public List<Integer> getRpm() {
        return rpm;
    }

    public void setRpm(List<Integer> rpm) {
        this.rpm = rpm;
    }

    public List<Integer> getTiltAngle() {
        return tiltAngle;
    }

    public void setTiltAngle(List<Integer> tiltAngle) {
        this.tiltAngle = tiltAngle;
    }

    public WarningsModel getWarnings() {
        return warnings;
    }

    public void setWarnings(WarningsModel warnings) {
        this.warnings = warnings;
    }
}
