package com.fourbtech.stickynote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fourbtech.stickynote.model.weather.WeatherResponseModel;
import com.fourbtech.stickynote.repository.WeatherRepository;

public class WeatherViewModel extends AndroidViewModel {

    private WeatherRepository weatherRepository;
    private MutableLiveData<WeatherResponseModel> weatherResponseModelLiveData;

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        weatherRepository = new WeatherRepository(application);
        weatherResponseModelLiveData = weatherRepository.getWeatherResponseModel();
    }

    public LiveData<WeatherResponseModel> getWeatherData() {
        return weatherResponseModelLiveData;
    }

}
