package com.example.sensorapp.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.sensorapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailAdapter extends BaseAdapter {
    private List<Float> values = new ArrayList<>();

    public DetailAdapter() {}

    public void setValues(@Nullable List<Float> values) {
        if (values == null) {
            values = new ArrayList<>();
        }
        this.values = values;
    }

    @Override
    public int getCount() {
        return (values != null) ? values.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return (exists(position)) ? values.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(container.getContext()).inflate(R.layout.list_item_main, null);
        }

        String format = "%0" + String.valueOf(getCount()).length() + "d";
        int base = 1;
        ((TextView) convertView.findViewById(R.id.index)).setText(String.format(Locale.JAPAN, format, position + base));
        ((TextView) convertView.findViewById(R.id.text)).setText(String.valueOf(getItem(position)));
        convertView.findViewById(R.id.image).setVisibility(View.GONE);
        return convertView;
    }

    private boolean exists(int position) {
        return (values != null) && (values.size() > position);
    }
}
