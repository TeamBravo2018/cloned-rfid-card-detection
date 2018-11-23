package com.cit.restapi.validationrequest.config;

import com.cit.restapi.validationrequest.mapper.AccessRequestPreviousMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddPrevMapperConfig {

    public static final AccessRequestPreviousMapper ADD_PREV_MAPPER = Mappers.getMapper( AccessRequestPreviousMapper.class );

    @Bean
    AccessRequestPreviousMapper accessRequestPreviousMapper() {
        return ADD_PREV_MAPPER;
    }


}
