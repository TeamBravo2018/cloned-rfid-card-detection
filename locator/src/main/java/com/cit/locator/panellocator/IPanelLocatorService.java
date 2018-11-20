package com.cit.locator.panellocator;

import com.cit.common.om.access.device.RfidReaderPanel;

/**
 * Created by odziea on 11/14/2018.
 */
public interface IPanelLocatorService {

    RfidReaderPanel findPanelById(String panelId);
}
