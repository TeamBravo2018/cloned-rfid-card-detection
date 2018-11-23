package com.cit.restapi.validationrequest;

import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.common.om.access.token.RfidBadge;
import com.cit.restapi.common.mapper.CommonMapper;
import com.cit.restapi.common.mapper.datetime.ZoneDateTimeMapper;
import com.cit.restapi.rfidpanel.mapper.CloneDetectionResultMapper;
import com.cit.restapi.validationrequest.dto.RfidPanelAccessRequestPreviousDto;
import com.cit.restapi.validationrequest.mapper.AccessRequestPreviousMapper;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


public class RfidPanelPreviousResourceTest {

    @Autowired
    AccessRequestPreviousMapper accessRequestPreviousMapper;

    @Autowired
    CloneDetectionResultMapper cloneDetectionResultMapper;


    @Before
    public void setup() {
        accessRequestPreviousMapper = Mappers.getMapper( AccessRequestPreviousMapper.class );
        cloneDetectionResultMapper = Mappers.getMapper( CloneDetectionResultMapper.class );
    }

    @Test
    public void test () {
        boolean allowed = true;
        String cardId = "3307775e-15ac-415f-a99c-e978856c8ec0";
        String panelId = "5e11d5ee-7715-4080-bfe6-25c66d8ce821";
        long timestamp = 1542993704978l;


        RfidPanelAccessRequestPreviousDto requestDto =  new RfidPanelAccessRequestPreviousDto();
        requestDto.setAllowed(allowed);
        requestDto.setCardId(cardId);
        requestDto.setPanelId(panelId);
        requestDto.setTimeStamp(timestamp);

        AccessRequest<RfidBadge, RfidReaderPanel> accessRequest = accessRequestPreviousMapper.dtoToDomain(requestDto);

        long transformedTime = ZoneDateTimeMapper.asLong(accessRequest.getAccessTime());

        assertEquals(timestamp,transformedTime);
    }




}