package com.cit.restapi.common.mapper.datetime;

import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Created by odziea on 11/18/2018.
 */
@Mapper
public class ZoneDateTimeMapper {

    public static long asLong(ZonedDateTime zonedDateTime){
        return zonedDateTime.toInstant().toEpochMilli();
    }

    /**
     * efoy -  convert Long timestamp to ZonedDateTime
     * @param timeStamp
     * @return ZonedDateTime with timestamp value
     */
    public static ZonedDateTime asZonedDateTime(Long timeStamp ){
        return Instant.ofEpochMilli(timeStamp).atZone(ZoneOffset.UTC);

    }


}



