package com.acjl.foodmenu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.acjl.foodmenu.model.weather.WeatherResponseModel;
import com.acjl.foodmenu.repository.WeatherRepository;

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
