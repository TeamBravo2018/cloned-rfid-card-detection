package com.cit.clonedetection.rules;

import com.cit.common.om.access.request.AccessRequest;

public class DistanceBetweenRequests implements IDistanceCalculator {
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

        return IDistanceCalculator.distance(lat1,lat2,lon1,lon2,el1,el2);
    }
}
