package com.cit.restapi.common.mapper;

import com.cit.common.om.access.request.AccessRequest;
import com.cit.restapi.rfidpanel.dto.AccessRequestDto;
import org.mapstruct.Mapper;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * Created by odziea on 11/18/2018.
 */
@Mapper(config=MapperConfiguration.class)
public interface CommonMapper {

    default ZonedDateTime timestampToZoneDateTime(long timestamp){
        Instant i = Instant.ofEpochSecond(timestamp);
        return ZonedDateTime.ofInstant(i, ZonedDateTime.now().getZone());
    }

    default long asLong(ZonedDateTime zonedDateTime){

        return zonedDateTime.toInstant().toEpochMilli();
    }

}
