package com.cit.clonedetection.services;


import com.cit.clonedetection.model.Distance;
import com.cit.clonedetection.model.Location;
import com.cit.clonedetection.rules.PanelDistanceCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocalDistanceService implements IDistanceService {


    public LocalDistanceService() {
        // No action required, just adding default constructor
    }

    public Distance execute(Location current, Location previous, Mode mode ) {

        Distance distance;

        double dis = getTravelDistanceInMtrs(current, previous);
        int dur = getTravelAvarageWalkDurationBetweenTwoLocations(dis);

        distance = Distance.builder()
                .distance(dis)
                .duration(dur)
                .mode(mode.toString())
                .status("OK")
                .build();

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
    private int getTravelDistanceInMtrs(Location current, Location previous) {
        double travelDistanceMtrsBetweenGPSPoints  = PanelDistanceCalculator.distanceInMtrsBetweenTwoLocations(current, previous);
        if (log.isDebugEnabled()) {
            log.debug("Travel distance in Mtr={} \n CurrentLocation={} \n PreviousLocation = {}", travelDistanceMtrsBetweenGPSPoints, current, previous );
        }
        return (int)travelDistanceMtrsBetweenGPSPoints;
    }


}



