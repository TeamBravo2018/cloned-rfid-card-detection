package com.cit.common.om.location;

/**
 * Class to represent a site building
 */
public class Building {

    private GeoLocation coordinates;
    private String name;
    private BuildingSite site;
    private Address address;

    public Building() {
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

    public BuildingSite getSite() {
        return site;
    }

    public void setSite(BuildingSite site) {
        this.site = site;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Building{" +
                "coordinates=" + coordinates +
                ", name='" + name + '\'' +
                ", site=" + site +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Building building = (Building) o;

        if (coordinates != null ? !coordinates.equals(building.coordinates) : building.coordinates != null)
            return false;
        if (name != null ? !name.equals(building.name) : building.name != null) return false;
        if (site != null ? !site.equals(building.site) : building.site != null) return false;
        return address != null ? address.equals(building.address) : building.address == null;
    }

    @Override
    public int hashCode() {
        int result = coordinates != null ? coordinates.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (site != null ? site.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
