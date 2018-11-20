package com.cit.locator.distance.googleapi;

import com.cit.common.om.location.GeoLocation;
import com.cit.locator.distance.om.Distance;
import com.cit.locator.distance.om.TravelMeans;

/**
 * Created by odziea on 11/20/2018.
 */
public interface IGoogleApiService {
    Distance execute(GeoLocation current, GeoLocation previous, TravelMeans travelMeans );
}
