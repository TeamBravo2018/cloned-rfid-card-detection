package com.cit.common.om.location;

/**
 * Class to represent a building site.
 */
public class BuildingSite {
    private GeoLocation coordinates;
    private String name;
    private Address address;

    public BuildingSite() {
    }

    public GeoLocation getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GeoLocation coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
