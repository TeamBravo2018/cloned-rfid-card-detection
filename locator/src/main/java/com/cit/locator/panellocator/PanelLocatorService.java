package com.cit.locator.panellocator;

import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.locator.panellocator.uuidpanellocator.IUuidPanelLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by odziea on 11/14/2018.
 */
@Service
public class PanelLocatorService implements IPanelLocatorService {

    @Autowired
    IUuidPanelLocatorService uuidPanelLocatorService;

    public RfidReaderPanel findPanelById(String panelId){
        return uuidPanelLocatorService.findPanelById(panelId);
    }

}
