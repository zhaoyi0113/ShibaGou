package com.example.yihanwang.myapplication.entities;

import io.realm.RealmObject;

/**
 * Created by joey on 10/9/17.
 */

public class Location extends RealmObject {


    private double latitude;

    private double longtitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
