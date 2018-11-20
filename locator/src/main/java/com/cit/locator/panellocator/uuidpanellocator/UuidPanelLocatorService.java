package com.cit.locator.panellocator.uuidpanellocator;

import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.core.rest.RestTemplateService;
import com.cit.locator.panellocator.uuidpanellocator.config.UuidLocatorUri;
import com.cit.locator.panellocator.uuidpanellocator.dto.UuidLocatorLocationDto;
import com.cit.locator.panellocator.uuidpanellocator.mapper.UuidLocatorLocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by odziea on 11/18/2018.
 */
@Service
public class UuidPanelLocatorService extends RestTemplateService implements IUuidPanelLocatorService {

    @Autowired
    private UuidLocatorUri uuidLocatorUri;

    @Autowired
    UuidLocatorLocationMapper uuidLocatorLocationMapper;

    public RfidReaderPanel findPanelById(String panelId){

        String endpoint = uuidLocatorUri.getPanels() + panelId;

        UuidLocatorLocationDto uuidLocatorLocationDto = (UuidLocatorLocationDto)this.getForObject(endpoint, UuidLocatorLocationDto.class);

        RfidReaderPanel rfidReaderPanel = uuidLocatorLocationMapper.dtoToDomain(uuidLocatorLocationDto);
        rfidReaderPanel.setId(panelId);

        return rfidReaderPanel;
    }

    @Override
    public String getRootUri() {
        return uuidLocatorUri.getRoot();
    }
}
