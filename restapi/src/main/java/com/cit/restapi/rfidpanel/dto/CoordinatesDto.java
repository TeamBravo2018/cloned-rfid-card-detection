package com.cit.restapi.rfidpanel.dto;

/**
 * Created by odziea on 11/18/2018.
 */
public class CoordinatesDto {
    private double longitude;
    private double latitude;

    public CoordinatesDto() {
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
