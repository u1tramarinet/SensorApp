package com.example.sensorapp.detail;

import android.hardware.Sensor;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DetailViewModel extends ViewModel {
    private MutableLiveData<Sensor> sensor = new MutableLiveData<>();

    public LiveData<Sensor> sensor() {
        return sensor;
    }

    public void sensor(@NonNull Sensor sensor) {
        this.sensor.postValue(sensor);
    }
}
