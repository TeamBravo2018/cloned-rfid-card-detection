package com.cit.restapi.validationrequest;

import com.cit.clonedetection.CloneDetectionResult;
import com.cit.clonedetection.ICloneDetectionService;
import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.common.om.access.token.RfidBadge;
import com.cit.restapi.rfidpanel.dto.CloneDetectionResultDto;
import com.cit.restapi.rfidpanel.dto.MQTTCloneDetectionResultDto;
import com.cit.restapi.rfidpanel.mapper.CloneDetectionResultMapper;
import com.cit.restapi.rfidpanel.mapper.MQTTCloneDetectionResultMapper;
import com.cit.restapi.rfidpanel.mapper.MQTTCloneDetectionResultMapperToJson;
import com.cit.restapi.validationrequest.dto.RfidPanelAccessRequestPreviousDto;
import com.cit.restapi.validationrequest.mapper.AccessRequestPreviousMapper;
import com.cit.notifier.service.NotifierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.TimeZone;

import static me.prettyprint.hector.api.beans.AbstractComposite.log;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@Api(value = "api/panels/request", description = "RFID panel requests")
@RestController
@RequestMapping(value = "api/panels", produces = "application/json" )
public class RfidPanelPreviousResource {

    @Autowired
    ICloneDetectionService cloneDetectionService;

    @Autowired
    AccessRequestPreviousMapper accessRequestPreviousMapper;

    @Autowired
    CloneDetectionResultMapper cloneDetectionResultMapper;

    @Autowired
    MQTTCloneDetectionResultMapper mQTTCloneDetectionResultMapper;

    @Autowired
    NotifierService notifierService;


    @ApiOperation("Validation check against possible clone card - GET Params - USED FOR TESTING ONLY WILL BE DISABLED IN PRODUCTION")
    @RequestMapping(
            value = "/addprev",
            method = GET)
    @ResponseBody
    public ResponseEntity<CloneDetectionResultDto> getAddPreviousForTesting(
            @RequestParam("panelId") String panelId,
            @RequestParam("cardId") String cardId,
            @RequestParam("allowed") boolean allowed ,
            @RequestParam(value = "timeStamp", required = false) String timeStamp
    )
    {
        return getValidationCommon(panelId,cardId,allowed,timeStamp);
    }


    @ApiOperation("Validation check against possible clone card - GET Parameters")
    @RequestMapping(
            value = "/request-params",
            method = GET)
    @ResponseBody
    public ResponseEntity<CloneDetectionResultDto> getValidationRequest(
            @RequestParam("panelId") String panelId,
            @RequestParam("cardId") String cardId,
            @RequestParam("allowed") boolean allowed
    )
    {
        return getValidationCommon(panelId,cardId,allowed,null);
    }


    public ResponseEntity<CloneDetectionResultDto> getValidationCommon(
            String panelId,
            String cardId,
            boolean allowed ,
            String timeStamp
    )
    {

        long time;

        if (timeStamp==null) {
            time = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTimeInMillis();
        } else {
            time = Long.parseLong(timeStamp);
        }

        RfidPanelAccessRequestPreviousDto requestDto =  new RfidPanelAccessRequestPreviousDto();
        requestDto.setAllowed(allowed);
        requestDto.setCardId(cardId);
        requestDto.setPanelId(panelId);
        requestDto.setTimeStamp(time);

        AccessRequest<RfidBadge, RfidReaderPanel> accessRequest = accessRequestPreviousMapper.dtoToDomain(requestDto);

        CloneDetectionResult cloneValidationResult = cloneDetectionService.checkForClonedCard(accessRequest);

        cloneDetectionService.setEventListener( (CloneDetectionResult cloneDetectionResult) -> {

            log.debug("Clone detection result payload for subscribed MQTT Listeners = {}", cloneDetectionResult);

            String mqttMessageString = MQTTCloneDetectionResultMapperToJson.toJsonString(cloneDetectionResult,mQTTCloneDetectionResultMapper);
            log.debug("Clone detection result payload for subscribed MQTT Listeners (json) = {}", mqttMessageString);
            notifierService.publish(mqttMessageString);
            // ** Anna **
            // publishing results to web socket potentially?

        } );

        CloneDetectionResultDto cloneDetectionResultDto;
        cloneDetectionResultDto = cloneDetectionResultMapper.domainToDto(cloneValidationResult);

        return new ResponseEntity<>(cloneDetectionResultDto, HttpStatus.OK);
    }

}
