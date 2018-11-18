package com.cit.clonedetection;

import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.locator.panellocator.IPanelLocatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by odziea on 11/12/2018.
 */
@Service
public class CloneDetectionService implements ICloneDetectionService{

    @Autowired
    IPanelLocatorService panelLocatorService;

    @Override
    public CloneDetectionResult checkForClonedCard(AccessRequest accessRequest) {

        RfidReaderPanel readerPanel = panelLocatorService.findPanelById(accessRequest.getAccessIssuer().getId());

        return null;
    }
}
