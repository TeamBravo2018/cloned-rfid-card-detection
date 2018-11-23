package com.cit.locator.panellocator.dto;

/**
 * Created by odziea on 11/18/2018.
 */
public class UuidLocatorLocationDto {

    private int altitude;
    private String relativeLocation;
    private UuidLocatorGpsCoordinatesDto coordinates;

    public UuidLocatorLocationDto() {
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

    public UuidLocatorGpsCoordinatesDto getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(UuidLocatorGpsCoordinatesDto coordinates) {
        this.coordinates = coordinates;
    }
}
