package com.cit.common.om.access.device;

import com.cit.common.om.location.Building;

/**
 * Created by odziea on 11/12/2018.
 */
public class RfidReaderPanel extends TokenReader{

    private Building building;

    public RfidReaderPanel() {
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

}
