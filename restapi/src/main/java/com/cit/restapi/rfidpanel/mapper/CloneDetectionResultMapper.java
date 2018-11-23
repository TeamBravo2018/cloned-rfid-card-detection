package com.cit.restapi.rfidpanel.mapper;

import com.cit.clonedetection.CloneDetectionResult;
import com.cit.restapi.common.mapper.CommonMapper;
import com.cit.restapi.rfidpanel.dto.CloneDetectionResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

/**
 * Created by odziea on 11/18/2018.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CloneDetectionResultMapper extends AccessRequestMapper {
    @Mappings({
            @Mapping(source = "accessRequest.requestGranted", target = "allowAccess"),
            @Mapping(source = "genuineCard", target = "validEvent"),
            @Mapping(source = "accessRequest", target = "currentEvent"),
            @Mapping(source = "previousAccessRequest", target = "previousEvent")
    })
    CloneDetectionResultDto domainToDto(final CloneDetectionResult cloneDetectionResult);
}
