package com.cit.common.om.access.device;

import com.cit.common.om.location.GeoLocation;

/**
 * Class to represent a token reader device
 */
public abstract class TokenReader {

    private GeoLocation geoLocation;

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

}
