package com.example.sensorapp.main;

import android.hardware.Sensor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sensorapp.R;

import java.util.List;
import java.util.Locale;

public class MainAdapter extends BaseAdapter {
    private List<Sensor> sensorList;

    public MainAdapter(@NonNull List<Sensor> sensorList) {
        this.sensorList = sensorList;
    }

    @Override
    public int getCount() {
        return (sensorList != null) ? sensorList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (exists(position)) ? sensorList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(container.getContext()).inflate(R.layout.list_item_main, null);
        }

        String format = "%0" + String.valueOf(getCount()).length() + "d";
        int base = 1;
        ((TextView) convertView.findViewById(R.id.index)).setText(String.format(Locale.JAPAN, format, position + base));
        ((TextView) convertView.findViewById(R.id.text)).setText(getSensorInfo((Sensor) getItem(position)));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem((Sensor) getItem(position));
            }
        });
        return convertView;
    }

    protected void onClickItem(@Nullable Sensor sensor) {

    }

    private boolean exists(int position) {
        return (sensorList != null) && (sensorList.size() > position);
    }

    private String getSensorInfo(Sensor sensor) {
        StringBuilder builder = new StringBuilder();
        builder.append(sensor.getName());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            builder.append(String.format(Locale.JAPAN, "\n%s", sensor.getStringType()));
        }
        return builder.toString();
    }
}
