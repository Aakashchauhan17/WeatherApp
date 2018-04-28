package com.arrow.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MyReportOnMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<WeatherModel> weatherModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_report_on_map);

        weatherModels = new ArrayList<>();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        new GetReport().execute();
    }


    private class GetReport extends AsyncTask<Void, Void, Void> {

        DBHelper dbHelper;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dbHelper = new DBHelper(MyReportOnMapActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            weatherModels = (ArrayList<WeatherModel>) dbHelper.getAllReport();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (weatherModels.size() > 0) {
                for (WeatherModel weatherModel : weatherModels) {
                    LatLng markerLL = new LatLng(weatherModel.getLat(), weatherModel.getLng());
                    mMap.addMarker(new MarkerOptions().position(markerLL).title(weatherModel.getTitle()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(markerLL));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
                }
            }
        }
    }
}
