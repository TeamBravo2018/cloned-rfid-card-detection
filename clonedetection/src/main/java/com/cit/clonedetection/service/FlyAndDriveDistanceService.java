package com.cit.clonedetection.service;


import com.cit.clonedetection.models.Distance;
import com.cit.common.om.location.GeoLocation;
import com.cit.clonedetection.rules.IDistanceService;
import com.cit.clonedetection.rules.DistanceBetweenLocations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlyAndDriveDistanceService implements IDistanceService {


    public FlyAndDriveDistanceService() {
        // No action required, just adding default constructor
    }

    public Distance execute(GeoLocation current, GeoLocation previous, Mode mode ) {

        Distance distance;

        // assume different country

        double dis = getTravelDistanceInMtrs(current, previous);
        int dur = getTravelAvarageFlyDurationBetweenTwoLocations(dis);

        distance = Distance.builder()
                .distance(dis)
                .duration(dur)
                .mode(mode.toString())
                .status("OK")
                .build();

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

        double travelDistanceMtrsBetweenGPSPoints  = DistanceBetweenLocations.distanceInMtrsBetweenTwoLocations(current, previous);
        if (log.isDebugEnabled()) {
            log.debug("Travel distance in Mtr={} \n CurrentLocation={} \n PreviousLocation = {}", travelDistanceMtrsBetweenGPSPoints, current, previous );
        }
        return (int)travelDistanceMtrsBetweenGPSPoints;
    }


}



