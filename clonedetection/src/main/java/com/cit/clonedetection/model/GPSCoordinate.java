package com.cit.clonedetection.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GPSCoordinate
{
    // GPS latitude, in decimal format
    private double latitude;

    // GPS longitude, in decimal format
    private double longitude;

}