package com.cit.clonedetection.services;


import com.cit.clonedetection.model.Distance;
import com.cit.clonedetection.model.Location;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class DistanceFacadeService implements IDistanceService {

    // cache all distance calculations in a map
    private Map<String,Distance> distanceMap = new HashMap<>();

    private GoogleDistanceService googleDistanceService;
    private LocalDistanceService localDistanceService;
    private FlyAndDriveDistanceService flyAndDriveDistanceService;

    @Autowired
    public DistanceFacadeService(GoogleDistanceService googleDistanceService, LocalDistanceService localDistanceService, FlyAndDriveDistanceService flyAndDriveDistanceService) {
        this.googleDistanceService = googleDistanceService;
        this.localDistanceService = localDistanceService;
        this.flyAndDriveDistanceService = flyAndDriveDistanceService;
    }

    @Override
    public Distance execute(Location current, Location previous, Mode mode) {
        Distance distance;

        String hash = toStringHash(current,previous,mode);

        // check have we done this calculation before
        distance = distanceMap.get(hash);

        if(distance== null) {

            // if not go and do the calculations
            if (mode == Mode.driving) {
                distance = googleDistanceService.execute(current, previous, mode);
            } else if (mode == Mode.walking) {
                distance = localDistanceService.execute(current, previous, mode);
            } else {
                distance = flyAndDriveDistanceService.execute(current, previous, mode);
            }

            // store in the cache so we don't have to do the calculation again
            distanceMap.put(hash,distance);

        }

        return distance;
    }


    /**
     * Create a hash to we can look up previous calculation without having to redo
     * @param current
     * @param previous
     * @param mode
     * @return
     */
    private String toStringHash(Location current, Location previous, Mode mode) {
        return String.format("%s-$s-$s",current,previous,mode);
    }


}
