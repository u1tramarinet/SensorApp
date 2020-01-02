package com.example.sensorapp.main;

import android.hardware.Sensor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sensorapp.Event;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<Event<Sensor>> showDetailEvent = new MutableLiveData<>();
    private final MutableLiveData<String> title = new MutableLiveData<>();

    public LiveData<Event<Sensor>> showDetail() {
        return showDetailEvent;
    }

    public LiveData<String> title() {
        return title;
    }

    public void showDetail(@NonNull Sensor sensor) {
        showDetailEvent.postValue(new Event<Sensor>(sensor));
    }

    public void updateTitle(@Nullable String title) {
        if (title == null) {
            title = "SensorApp";
        }
        this.title.postValue(title);
    }
}
