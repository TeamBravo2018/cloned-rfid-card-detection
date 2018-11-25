package com.cit.restapi.rfidpanel.mapper;

import com.cit.clonedetection.CloneDetectionResult;
import com.cit.restapi.rfidpanel.dto.CloneDetectionResultDto;
import com.cit.restapi.rfidpanel.dto.MQTTCloneDetectionResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * Created by odziea on 11/18/2018.
 * adapted by efoy 23 Nov 2018
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MQTTCloneDetectionResultMapper extends AccessRequestMapper {

    @Mappings({
            @Mapping(expression = "java( ( cloneDetectionResult.isGenuineCard() == true ) ? MQTTCloneDetectionResultDto.POSSIBLE_EVENT_TITLE  :  MQTTCloneDetectionResultDto.IMPOSSIBLE_EVENT_TITLE )", target = "title"),
            @Mapping(expression = "java( ( cloneDetectionResult.isGenuineCard() == true ) ? MQTTCloneDetectionResultDto.POSSIBLE_EVENT_DESC :  MQTTCloneDetectionResultDto.IMPOSSIBLE_EVENT_DESC )", target = "description"),
            @Mapping(expression = "java( ( cloneDetectionResult.isGenuineCard() == true ) ? \"Low\"  :  \"High\" )", target = "severity"),
            @Mapping(source = "accessRequest", target = "currentEvent"),
            @Mapping(source = "previousAccessRequest", target = "previousEvent")
    })
    MQTTCloneDetectionResultDto domainToDto(final CloneDetectionResult cloneDetectionResult);
}
