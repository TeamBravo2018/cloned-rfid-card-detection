package com.cit.locator.distance.service;



import com.cit.common.om.location.GeoLocation;
import com.cit.locator.distance.googleapi.IGoogleApiService;
import com.cit.locator.distance.om.TravelMeans;
import com.cit.locator.distance.utils.DistanceUtils;
import com.cit.locator.distance.om.Distance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class FlyAndDriveDistanceService implements IGoogleApiService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Distance execute(GeoLocation current, GeoLocation previous, TravelMeans mode ) {

        Distance distance = new Distance();

        // assume different country

        double dis = getTravelDistanceInMtrs(current, previous);
        int dur = getTravelAvarageFlyDurationBetweenTwoLocations(dis);


        distance.setLength(dis);
        distance.setDuration(dur);
        distance.setTravelMeans(mode);

        return distance;
    }

    /**
     * Calculate Average Fly Time in seconds between two locations
     * Time = Distance / Speed;
     * assume average of 200 mtrs per second
     * @param distance Distance to travel in Mtrs
     * @return Average fly Time in seconds between two locations
     */
    private int getTravelAvarageFlyDurationBetweenTwoLocations(double distance) {
        return (int)(distance / 200.0);
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



