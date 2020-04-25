package com.example.sensorapp.main;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.sensorapp.R;

import java.util.List;

public class MainFragment extends Fragment {

    private SensorManager sensorManager;
    private ListView listView;

    private MainViewModel viewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.main_fragment, container, false);
        listView = root.findViewById(R.id.listView);
        listView.setEmptyView(root.findViewById(R.id.empty));
        setUpSensorListView();
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setUpSensorListView() {
        Context context = getContext();
        if (context == null) return;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager == null) return;
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        MainAdapter adapter = new MainAdapter(sensorList) {
            @Override
            protected void onClickItem(@Nullable Sensor sensor) {
                if (sensor == null) return;
                viewModel.showDetail(sensor);
            }
        };

        listView.setAdapter(adapter);
    }

}
