package com.fourbtech.stickynote.network;

import com.fourbtech.stickynote.model.weather.WeatherResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("onecall")
    Call<WeatherResponseModel> getWeatherResponse(
            @Query("appId") String appId,
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("units") String units,
            @Query("exclude") String exclude
    );

}
