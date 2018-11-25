package com.cit.restapi.rfidpanel.mapper;

import com.cit.clonedetection.CloneDetectionResult;
import com.cit.restapi.rfidpanel.dto.MQTTCloneDetectionResultDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MQTTCloneDetectionResultMapperToJson {
    private static final Logger log = LoggerFactory.getLogger(MQTTCloneDetectionResultMapperToJson.class);

    public static String toJsonString( CloneDetectionResult cloneDetectionResult, MQTTCloneDetectionResultMapper mqttMapper ) {

        String result = null;

        MQTTCloneDetectionResultDto mQTTCloneDetectionResultDto = mqttMapper.domainToDto(cloneDetectionResult);
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mQTTCloneDetectionResultDto);
        } catch (JsonProcessingException e) {
            log.error("Issue converting MQTT message = {}", e);
        }

        return result;
    }




}
