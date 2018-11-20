package com.cit.locator.distance.service;

import com.cit.common.om.location.GeoLocation;
import com.cit.locator.distance.om.Distance;
import com.cit.locator.distance.om.TravelMeans;

public interface IDistanceService {
    Distance execute(GeoLocation current, GeoLocation previous, TravelMeans travelMeans );
}
