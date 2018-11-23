package com.cit.restapi.common.mapper.datetime;

import org.junit.Test;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;

public class ZoneDateTimeMapperTest {

    @Test
    public void asLong() throws Exception {

        long time = 1542993704978L;

        ZonedDateTime zonedDateTime = ZoneDateTimeMapper.asZonedDateTime(time);

        long convertetime =  ZoneDateTimeMapper.asLong(zonedDateTime);

        assertEquals(time,convertetime);
    }


}