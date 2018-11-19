package com.cit.clonedetection.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Distance {
    private double distance;
    private int duration;
    private String mode;
    private String status;
}
