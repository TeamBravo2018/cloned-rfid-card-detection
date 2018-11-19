package com.cit.clonedetection.service;


import com.cit.clonedetection.models.Distance;
import com.cit.clonedetection.rules.IDistanceService;
import com.cit.common.om.location.GeoLocation;
import org.springframework.beans.factory.annotation.Autowired;

public class DistanceFacadeService implements IDistanceService {

    private GoogleApiService googleDistanceService;
    private LocalDistanceService localDistanceService;
    private FlyAndDriveDistanceService flyAndDriveDistanceService;

    @Autowired
    public DistanceFacadeService(GoogleApiService googleDistanceService, LocalDistanceService localDistanceService, FlyAndDriveDistanceService flyAndDriveDistanceService) {
        this.googleDistanceService = googleDistanceService;
        this.localDistanceService = localDistanceService;
        this.flyAndDriveDistanceService = flyAndDriveDistanceService;
    }

    @Override
    public Distance execute(GeoLocation current, GeoLocation previous, Mode mode) {
        Distance distance;

        if (mode==Mode.driving) {
            distance = googleDistanceService.execute(current,previous,mode);
        }  else if (mode == Mode.walking){
            distance = localDistanceService.execute(current,previous,mode);
        } else {
            distance = flyAndDriveDistanceService.execute(current,previous,mode);
        }

        return distance;
    }


}
