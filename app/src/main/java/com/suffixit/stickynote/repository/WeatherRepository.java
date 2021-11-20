package com.suffixit.stickynote.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.suffixit.stickynote.model.weather.WeatherResponseModel;
import com.suffixit.stickynote.network.APIInterface;
import com.suffixit.stickynote.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {

    private static final String TAG = "WeatherRepository";

    private APIInterface apiInterface;
    private MutableLiveData<WeatherResponseModel> weatherResponseModelLiveData = new MutableLiveData();

    public WeatherRepository(Application app) {
        apiInterface = RetrofitClient.getRetrofitClient().create(APIInterface.class);
    }

    public MutableLiveData<WeatherResponseModel> getWeatherResponseModel() {

        Log.d(TAG, "getWeatherResponseModel: ");
        
        Call<WeatherResponseModel> call = apiInterface.getWeatherResponse(
                "921b4588785edc17fd8f536f0990d05b",
                23.777176,
                90.399452,
                "metric",
                "hourly,minutely,daily"
        );

        call.enqueue(new Callback<WeatherResponseModel>() {
            @Override
            public void onResponse(Call<WeatherResponseModel> call, Response<WeatherResponseModel> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    weatherResponseModelLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponseModel> call, Throwable t) {
                //Toast.makeText(application, "error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return weatherResponseModelLiveData;
    }

}
