package com.cit.restapi.rfidpanel.config;

import com.cit.restapi.rfidpanel.mapper.AccessRequestMapper;
import com.cit.restapi.rfidpanel.mapper.CloneDetectionResultMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * efoy - resolving start up issues, which were not autowiring correctly
 */
@Configuration
public class RFIDPanelMapperConfig {
    public static final AccessRequestMapper ACCESS_REQUEST_MAPPER = Mappers.getMapper( AccessRequestMapper.class );
    public static final CloneDetectionResultMapper CLONE_DETECTION_RESULT_MAPPER = Mappers.getMapper( CloneDetectionResultMapper.class );

    @Bean
    AccessRequestMapper accessRequestMapper() {
        return ACCESS_REQUEST_MAPPER;
    }

    @Bean
    CloneDetectionResultMapper cloneDetectionResultMapper() { return CLONE_DETECTION_RESULT_MAPPER; }
}
