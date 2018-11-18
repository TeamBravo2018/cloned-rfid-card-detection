package com.cit.restapi.rfidpanel.dto;

/**
 * Created by odziea on 11/18/2018.
 */
public class LocationDto {

    private CoordinatesDto coordinates;
    private int altitude;
    private String relativeLocation;

    public LocationDto() {
    }

    public CoordinatesDto getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesDto coordinates) {
        this.coordinates = coordinates;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public String getRelativeLocation() {
        return relativeLocation;
    }

    public void setRelativeLocation(String relativeLocation) {
        this.relativeLocation = relativeLocation;
    }
}
