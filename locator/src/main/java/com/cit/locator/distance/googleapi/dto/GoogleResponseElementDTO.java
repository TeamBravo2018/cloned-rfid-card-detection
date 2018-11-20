package com.cit.locator.distance.googleapi.dto;

/**
 * Created by odziea on 11/20/2018.
 */
public class GoogleResponseElementDTO {

    private GoogleResponseValue distance;
    private GoogleResponseValue duration;
    private String status;

    public GoogleResponseValue getDistance() {
        return distance;
    }

    public void setDistance(GoogleResponseValue distance) {
        this.distance = distance;
    }

    public GoogleResponseValue getDuration() {
        return duration;
    }

    public void setDuration(GoogleResponseValue duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
