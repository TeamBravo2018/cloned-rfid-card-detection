package com.cit.clonedetection.rules;

import com.cit.common.om.location.GeoLocation;

public class DistanceBetweenLocations implements IDistanceCalculator {
    /**
     *
     * @param current   Current Access Event
     * @param previous  Prevous Access Event
     * @return distance in Mtrs between the two event's locations
     */

    public static double distanceInMtrsBetweenTwoLocations(GeoLocation current, GeoLocation previous) {

        double lat1 = current.getX();
        double lon1 = current.getY();
        double el1 = current.getZ();

        double lat2 = previous.getX();
        double lon2 = previous.getY();
        double el2 = previous.getZ();

        return IDistanceCalculator.distance(lat1,lat2,lon1,lon2,el1,el2);
    }
}
