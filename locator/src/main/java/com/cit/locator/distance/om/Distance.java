package com.cit.locator.distance.om;

public class Distance {
    private double length;
    private int duration;
    private TravelMeans travelMeans;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public TravelMeans getTravelMeans() {
        return travelMeans;
    }

    public void setTravelMeans(TravelMeans travelMeans) {
        this.travelMeans = travelMeans;
    }
}
