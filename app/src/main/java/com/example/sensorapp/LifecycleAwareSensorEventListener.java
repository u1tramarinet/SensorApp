package com.example.sensorapp;

import android.hardware.SensorEventListener;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public interface LifecycleAwareSensorEventListener extends SensorEventListener, LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void registerListener();
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void unregisterListener();
}
