package com.example.sensorapp;

import android.hardware.Sensor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sensorapp.main.MainViewModel;
import com.example.sensorapp.util.ActivityUtils;
import com.example.sensorapp.detail.DetailViewModel;
import com.example.sensorapp.detail.DetailFragment;
import com.example.sensorapp.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private MainViewModel mainViewModel;
    private DetailViewModel detailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        replaceFragmentInActivity(MainFragment.newInstance());

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.showDetail().observe(this, new Observer<Event<Sensor>>() {
            @Override
            public void onChanged(Event<Sensor> sensorEvent) {
                Sensor sensor = sensorEvent.getContentIfNotHandled();
                if (sensor == null) return;
                detailViewModel.sensor(sensor);
                replaceFragmentInActivity(DetailFragment.newInstance());
                mainViewModel.updateTitle(sensor.getName());
            }
        });
        mainViewModel.title().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                toolbar.setTitle(s);
            }
        });
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getFragments().size() == 1) {
            mainViewModel.updateTitle(null);
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    private void replaceFragmentInActivity(@NonNull Fragment fragment) {
        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
    }
}
