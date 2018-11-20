package com.cit.locator.distance.service;


import com.cit.common.om.location.GeoLocation;
import com.cit.locator.distance.googleapi.GoogleApiService;
import com.cit.locator.distance.om.Distance;
import com.cit.locator.distance.om.TravelMeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceFacadeService implements IDistanceService {

    @Autowired
    private GoogleApiService googleDistanceService;

    @Autowired
    private LocalDistanceService localDistanceService;
    @Autowired
    private FlyAndDriveDistanceService flyAndDriveDistanceService;

    @Override
    public Distance execute(GeoLocation current, GeoLocation previous, TravelMeans travelMeans) {
        Distance distance;

        if (travelMeans==TravelMeans.driving) {
            distance = googleDistanceService.execute(current,previous,travelMeans);
        }  else if (travelMeans == TravelMeans.walking){
            distance = localDistanceService.execute(current,previous,travelMeans);
        } else {
            distance = flyAndDriveDistanceService.execute(current,previous,travelMeans);
        }

        return distance;
    }


}
