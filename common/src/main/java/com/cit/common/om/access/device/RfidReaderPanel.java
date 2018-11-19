package com.cit.common.om.access.device;

import com.cit.common.om.location.Building;
import com.cit.common.om.location.GeoLocation;

/**
 * Created by odziea on 11/12/2018.
 */
public class RfidReaderPanel extends TokenReader{

    private Building building;

    public RfidReaderPanel() {
    }

    public RfidReaderPanel(String id) {
        super(id);
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

}
