package com.cit.locator.distance.service;


import com.cit.common.om.location.GeoLocation;
import com.cit.locator.distance.googleapi.IGoogleApiService;
import com.cit.locator.distance.om.Distance;
import com.cit.locator.distance.om.TravelMeans;
import com.cit.locator.distance.utils.DistanceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class LocalDistanceService implements IGoogleApiService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Distance execute(GeoLocation current, GeoLocation previous, TravelMeans travelMeans ) {

        Distance distance = new Distance();

        double dis = getTravelDistanceInMtrs(current, previous);
        int dur = getTravelAvarageWalkDurationBetweenTwoLocations(dis);

        distance.setLength(dis);
        distance.setDuration(dur);
        distance.setTravelMeans(travelMeans);

        return distance;
    }

    /**
     * Calculate Average walk Time in seconds between two locations
     * Time = Distance / Speed;
     * assume average of 1.4 mtrs per second
     * @param distance Distance to travel in Mtrs
     * @return Average walk Time in seconds between two locations
     */
    private int getTravelAvarageWalkDurationBetweenTwoLocations(double distance) {
        return (int)(distance / 1.4);
    }

    /**
     * Calculate distance in meters between GPS point A and GPS point B = traveldistanceMtrsBetweenGPSPoints
     * @return distance in Mtrs
     */
    private int getTravelDistanceInMtrs(GeoLocation current, GeoLocation previous) {
        double travelDistanceMtrsBetweenGPSPoints  = DistanceUtils.distanceInMtrsBetweenTwoLocations(current, previous);
        if (log.isDebugEnabled()) {
            log.debug("Travel distance in Mtr={} \n CurrentLocation={} \n PreviousLocation = {}", travelDistanceMtrsBetweenGPSPoints, current, previous );
        }
        return (int)travelDistanceMtrsBetweenGPSPoints;
    }


}



