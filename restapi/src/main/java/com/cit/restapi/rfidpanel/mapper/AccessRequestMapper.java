package com.cit.restapi.rfidpanel.mapper;

import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.access.device.TokenReader;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.common.om.access.token.RfidBadge;
import com.cit.restapi.common.mapper.CommonMapper;
import com.cit.restapi.common.mapper.datetime.ZoneDateTimeMapper;
import com.cit.restapi.rfidpanel.dto.AccessEventDto;
import com.cit.restapi.rfidpanel.dto.LocationDto;
import com.cit.restapi.rfidpanel.dto.RfidPanelAccessRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.time.ZonedDateTime;


/**
 * Mapper to handle mapping between entities representing an access request
 * and their respective DTOs (and vice versa)
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {ZoneDateTimeMapper.class })
public interface AccessRequestMapper extends CommonMapper {

    @Mappings({
            @Mapping(source="cardId", target="accessToken.tokenId"),
            @Mapping(source="panelId", target="accessIssuer.id"),
            @Mapping(source="allowed", target="requestGranted"),
            @Mapping(expression = "java(java.time.ZonedDateTime.now())", target="accessTime")
    })
    AccessRequest<RfidBadge, RfidReaderPanel> dtoToDomain(final RfidPanelAccessRequestDto rfidPanelAccessRequestDto);


    @Mappings({
            @Mapping(source="accessToken.tokenId", target="cardId"),
            @Mapping(source="accessIssuer.id", target="panelId"),
            @Mapping(source="accessTime", target="timestamp"),
            @Mapping(source="accessIssuer", target="location"),
            @Mapping(source="requestGranted", target="accessAllowed")

    })
    AccessEventDto domainToDto(final AccessRequest<RfidBadge, RfidReaderPanel> accessRequest);

    default <T extends TokenReader> LocationDto tokenReaderToLocationDto(final T tokenReader){
        if(tokenReader instanceof RfidReaderPanel){

        }
        return new LocationDto();
    }

}
