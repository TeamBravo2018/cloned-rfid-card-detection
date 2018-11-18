package com.cit.common.om.access.device;

import com.cit.common.om.location.GeoLocation;

/**
 * Class to represent a token reader device
 */
public abstract class TokenReader {

    private String id;

    public TokenReader() {
    }

    public TokenReader(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
