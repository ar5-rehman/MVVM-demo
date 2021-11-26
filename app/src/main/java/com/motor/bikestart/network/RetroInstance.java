package com.motor.bikestart.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {

    public static String api = "https://7gwm5i2564.execute-api.ap-southeast-1.amazonaws.com/dev/";

    public static Retrofit retrofit;

    public static Retrofit getInstance(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
