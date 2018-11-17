package com.cit.common.om.access.token;

import com.cit.common.om.location.Building;

/**
 * Class representing an RFID badge
 * @author odziea
 */
public class RfidBadge extends Token{

    private Building building;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
