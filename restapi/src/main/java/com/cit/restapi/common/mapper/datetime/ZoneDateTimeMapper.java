package com.cit.restapi.common.mapper.datetime;

import org.mapstruct.Mapper;

import java.time.ZonedDateTime;

/**
 * Created by odziea on 11/18/2018.
 */
@Mapper
public class ZoneDateTimeMapper {

    public long asLong(ZonedDateTime zonedDateTime){
        return zonedDateTime.toInstant().toEpochMilli();
    }
}
