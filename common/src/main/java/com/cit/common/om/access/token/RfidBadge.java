package com.cit.common.om.access.token;

import com.cit.common.om.location.Building;

/**
 * Class representing an RFID badge
 * @author odziea
 */
public class RfidBadge extends Token{

    public RfidBadge() {
    }

    public RfidBadge(String tokenId) {
        super(tokenId);
    }

    private Building building;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }


    @Override
    public String toString() {
        return "RfidBadge{" +
                "building=" + building +
                '}';
    }
}
