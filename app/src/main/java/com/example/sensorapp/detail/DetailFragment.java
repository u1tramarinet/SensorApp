package com.example.sensorapp.detail;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sensorapp.R;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment implements SensorEventListener {

    private DetailViewModel viewModel;
    private SensorManager sensorManager;
    private DetailAdapter adapter;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.detail_fragment, container, false);
        ListView listView = root.findViewById(R.id.listView);
        listView.setEmptyView(root.findViewById(R.id.empty));
        listView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(DetailViewModel.class);
        viewModel.sensor().observe(this, new Observer<Sensor>() {
            @Override
            public void onChanged(Sensor sensor) {
                unregisterListener();
                registerListener(sensor);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        adapter = new DetailAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterListener();
    }

    private void registerListener() {
        Sensor sensor = viewModel.sensor().getValue();
        registerListener(sensor);
    }

    private void registerListener(Sensor sensor) {
        if (sensor == null) return;
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    private void unregisterListener() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        List<Float> values = new ArrayList<>();
        for (float value : sensorEvent.values) {
            values.add(value);
        }
        adapter.setValues(values);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
