package com.cit.model;

@lombok.Data
@lombok.Builder
public class GPSCoordinate
{
    // GPS latitude, in decimal format
    private double latitude;

    // GPS longitude, in decimal format
    private double longitude;

}