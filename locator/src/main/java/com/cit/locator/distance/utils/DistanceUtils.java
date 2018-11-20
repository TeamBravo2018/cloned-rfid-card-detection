package com.cit.locator.distance.utils;

import com.cit.common.om.access.request.AccessRequest;
import com.cit.common.om.location.GeoLocation;

import java.time.Duration;
import java.time.ZonedDateTime;

public class DistanceUtils {
    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double calculateDistance(double lat1, double lat2, double lon1,
                                   double lon2, double el1, double el2) {
        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        double height = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }

    public static double distanceInMtrsBetweenTwoLocations(GeoLocation current, GeoLocation previous) {

        double lat1 = current.getX();
        double lon1 = current.getY();
        double el1 = current.getZ();

        double lat2 = previous.getX();
        double lon2 = previous.getY();
        double el2 = previous.getZ();

        return DistanceUtils.calculateDistance(lat1,lat2,lon1,lon2,el1,el2);
    }

    /**
     *
     * @param current   Current Access Request
     * @param previous  Prevous Access Request
     * @return distance in Mtrs between the two event's locations
     */
    // todo calculate the distance using interfaces provided
    public static double distanceInMtrsBetweenTwoEvents(AccessRequest current, AccessRequest previous) {

        double lat1 = current.getAccessIssuer().getGeoLocation().getX();
        double lon1 = current.getAccessIssuer().getGeoLocation().getX();
        double el1 = current.getAccessIssuer().getGeoLocation().getZ();

        double lat2 = previous.getAccessIssuer().getGeoLocation().getX();
        double lon2 = previous.getAccessIssuer().getGeoLocation().getY();
        double el2 = previous.getAccessIssuer().getGeoLocation().getZ();

        return DistanceUtils.calculateDistance(lat1,lat2,lon1,lon2,el1,el2);
    }

}
