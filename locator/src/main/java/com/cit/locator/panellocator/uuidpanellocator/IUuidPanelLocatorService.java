package com.cit.locator.panellocator.uuidpanellocator;

import com.cit.common.om.access.device.RfidReaderPanel;

/**
 * Created by odziea on 11/18/2018.
 */
public interface IUuidPanelLocatorService {

    RfidReaderPanel findPanelById(String panelId);
}
