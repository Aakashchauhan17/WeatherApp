package com.arrow.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MyReportActivity extends AppCompatActivity {

    ArrayList<WeatherModel> weatherModels;
    RecyclerView rv_weather;
    WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_report);

        weatherModels = new ArrayList<>();
        rv_weather = findViewById(R.id.rv_weather);
        weatherAdapter = new WeatherAdapter(MyReportActivity.this, weatherModels);
        rv_weather.setItemAnimator(new DefaultItemAnimator());
        rv_weather.setAdapter(weatherAdapter);

        DBHelper dbHelper = new DBHelper(MyReportActivity.this);
        weatherModels = (ArrayList<WeatherModel>) dbHelper.getAllReport();
        if (weatherModels.size() > 0) {
            weatherAdapter = new WeatherAdapter(MyReportActivity.this, weatherModels);
            rv_weather.setItemAnimator(new DefaultItemAnimator());
            rv_weather.setAdapter(weatherAdapter);
//            weatherAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_my_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_report:
                startActivity(new Intent(MyReportActivity.this, MyReportOnMapActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
