package com.arrow.weatherapp;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherModel implements Parcelable{

    private String title, message, time;
    private int image;
    private double lat, lng;


    protected WeatherModel(Parcel in) {
        title = in.readString();
        message = in.readString();
        image = in.readInt();
        lat = in.readDouble();
        lng = in.readDouble();
        time = in.readString();
    }

    public static final Creator<WeatherModel> CREATOR = new Creator<WeatherModel>() {
        @Override
        public WeatherModel createFromParcel(Parcel in) {
            return new WeatherModel(in);
        }

        @Override
        public WeatherModel[] newArray(int size) {
            return new WeatherModel[size];
        }
    };

    public WeatherModel() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(message);
        parcel.writeInt(image);
        parcel.writeDouble(lat);
        parcel.writeDouble(lng);
        parcel.writeString(time);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                ", image=" + image +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
