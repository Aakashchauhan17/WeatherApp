package com.arrow.weatherapp;

public class Constants {

    //  current lat-lng
    public double CURRENT_LATITUDE = 0;
    public double CURRENT_LONGITUDE = 0;

    public static final String KEY_LAT = "latitude";
    public static final String KEY_LONG = "longitude";

    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_TIMESTAMP = "timestamp";


    //  database
    public static final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS ";
    public static final String TABLE_REPORT = "report";

    public static final String CREATE_REPORT_TABLE = CREATE_QUERY + TABLE_REPORT + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_TITLE + " TEXT,"
            + KEY_MESSAGE + " TEXT,"
            + KEY_IMAGE + " TEXT,"
            + KEY_LAT + " TEXT,"
            + KEY_LONG + " TEXT,"
            + KEY_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ")";
}
