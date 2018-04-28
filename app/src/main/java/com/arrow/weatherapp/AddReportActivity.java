package com.arrow.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class AddReportActivity extends AppCompatActivity {

    ArrayList<WeatherModel> weatherModels;
    RecyclerView rv_weather;
    WeatherAdapter weatherAdapter;
    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent().hasExtra(Constants.KEY_LAT)){
            lat = getIntent().getDoubleExtra(Constants.KEY_LAT, 0);
        }
        if (getIntent().hasExtra(Constants.KEY_LONG)){
            lng = getIntent().getDoubleExtra(Constants.KEY_LONG, 0);
        }

        weatherModels = new ArrayList<>();
        rv_weather = findViewById(R.id.rv_weather);
        weatherAdapter = new WeatherAdapter(AddReportActivity.this, weatherModels);
        rv_weather.setItemAnimator(new DefaultItemAnimator());
        rv_weather.setAdapter(weatherAdapter);

        setupData();

    }

    private void setupData() {

        WeatherModel snowWM = new WeatherModel();
        snowWM.setTitle("Snow");
        snowWM.setMessage("Snowing heavily");
        snowWM.setImage(R.drawable.ic_snow_24dp);
        weatherModels.add(snowWM);

        WeatherModel sunnyWM = new WeatherModel();
        sunnyWM.setTitle("SUNNY");
        sunnyWM.setMessage("Sunny Day");
        sunnyWM.setImage(R.drawable.beach);
        weatherModels.add(sunnyWM);

        WeatherModel rainWM = new WeatherModel();
        rainWM.setTitle("RAIN");
        rainWM.setMessage("Raining heavily");
        rainWM.setImage(R.drawable.umbrella);
        weatherModels.add(rainWM);

        WeatherModel windWM = new WeatherModel();
        windWM.setTitle("WIND");
        windWM.setMessage("Winding heavily");
        windWM.setImage(R.drawable.palm);
        weatherModels.add(windWM);

        WeatherModel coldWM = new WeatherModel();
        coldWM.setTitle("Cold");
        coldWM.setMessage("Cold like a coca cola");
        coldWM.setImage(R.drawable.temperature);
        weatherModels.add(coldWM);

        WeatherModel hotWM = new WeatherModel();
        hotWM.setTitle("HOT");
        hotWM.setMessage("Hotter than hell");
        hotWM.setImage(R.drawable.sun1);
        weatherModels.add(hotWM);

        weatherAdapter.notifyDataSetChanged();
    }

}
