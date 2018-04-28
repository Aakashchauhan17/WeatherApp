package com.arrow.weatherapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "report_db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Constants.CREATE_REPORT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_REPORT);
        onCreate(sqLiteDatabase);
    }

    public long insertReport(WeatherModel weatherModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_TITLE,weatherModel.getTitle());
        values.put(Constants.KEY_MESSAGE,weatherModel.getMessage());
        values.put(Constants.KEY_IMAGE,weatherModel.getImage());
        values.put(Constants.KEY_LAT,String.valueOf(weatherModel.getLat()));
        values.put(Constants.KEY_LONG,String.valueOf(weatherModel.getLng()));

        long id = db.insert(Constants.TABLE_REPORT, null, values);

        db.close();

        return id;
    }

    public List<WeatherModel> getAllReport() {
        List<WeatherModel> reports = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Constants.TABLE_REPORT + " ORDER BY "
                + Constants.KEY_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                WeatherModel weatherModel = new WeatherModel();
                weatherModel.setTitle(cursor.getString(cursor.getColumnIndex(Constants.KEY_TITLE)));
                weatherModel.setMessage(cursor.getString(cursor.getColumnIndex(Constants.KEY_MESSAGE)));
                weatherModel.setImage(cursor.getInt(cursor.getColumnIndex(Constants.KEY_IMAGE)));
                weatherModel.setLat(Double.parseDouble(cursor.getString(cursor.getColumnIndex(Constants.KEY_LAT))));
                weatherModel.setLng(Double.parseDouble(cursor.getString(cursor.getColumnIndex(Constants.KEY_LONG))));
                weatherModel.setTime(cursor.getString(cursor.getColumnIndex(Constants.KEY_TIMESTAMP)));
                reports.add(weatherModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return reports;
    }
}
