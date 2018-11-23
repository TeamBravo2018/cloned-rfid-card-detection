package com.cit.restapi.common.mapper;


import com.cit.restapi.validationrequest.mapper.AccessRequestPreviousMapper;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

public class CommonMapperTest {

    @Autowired
    CommonMapper commonMapper;

    @Before
    public void setup() {
        commonMapper = Mappers.getMapper( CommonMapper.class );
    }

    @Test
    public void timestampToZoneDateTime() throws Exception {

        long time = 1542993704978L;

        ZonedDateTime zonedDateTime = commonMapper.timestampToZoneDateTime(time);


        long convertetime =  commonMapper.asLong(zonedDateTime);

        assertEquals(time,convertetime);
    }


}