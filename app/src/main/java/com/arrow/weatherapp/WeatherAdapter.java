package com.arrow.weatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{

    private Context context;
    private ArrayList<WeatherModel> weatherModels;
    DBHelper dbHelper;

    static class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tv_weather;
        AppCompatImageView iv_weather;
        FloatingActionButton fab_add;

        ViewHolder(View itemView) {
            super(itemView);
            tv_weather = itemView.findViewById(R.id.tv_weather);
            iv_weather = itemView.findViewById(R.id.iv_weather);
            fab_add = itemView.findViewById(R.id.fab_add);
        }
    }

    WeatherAdapter(Context context, ArrayList<WeatherModel> weatherModels) {
        this.context = context;
        this.weatherModels = weatherModels;

        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_weather_card, parent, false);

        return new ViewHolder(row_item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final WeatherModel weatherModel = weatherModels.get(position);
        holder.tv_weather.setText(weatherModel.getTitle());
        holder.iv_weather.setImageDrawable(ContextCompat.getDrawable(context, weatherModel.getImage()));
        holder.fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, " " + weatherModel.getTitle() + " is selected", Toast.LENGTH_SHORT).show();
                addReport(weatherModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherModels.size();
    }

    private void addReport(WeatherModel weatherModel) {
        weatherModel.setLat(((AddReportActivity)context).lat);
        weatherModel.setLng(((AddReportActivity)context).lng);
        long total = dbHelper.insertReport(weatherModel);
        Log.e("INSERT >>> ", "total " + total);
        ((AddReportActivity)context).finish();
    }




}
