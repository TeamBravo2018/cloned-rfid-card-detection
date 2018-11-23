package com.cit.restapi.rfidpanel;

import com.cit.clonedetection.CloneDetectionResult;
import com.cit.clonedetection.ICloneDetectionService;
import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.common.om.access.token.RfidBadge;
import com.cit.restapi.rfidpanel.dto.CloneDetectionResultDto;
import com.cit.restapi.rfidpanel.dto.RfidPanelAccessRequestDto;
import com.cit.restapi.rfidpanel.mapper.AccessRequestMapper;
import com.cit.restapi.rfidpanel.mapper.CloneDetectionResultMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static me.prettyprint.hector.api.beans.AbstractComposite.log;

@Slf4j
@Api(value = "api/panels/request", description = "RFID panel requests")
@RestController
@RequestMapping(value = "api/panels", produces = "application/json", consumes = "application/json")
public class RfidPanelResource {

    @Autowired
    ICloneDetectionService cloneDetectionService;

    @Autowired
<<<<<<< HEAD
    CloneDetectionResultMapper cloneDetectionResultMapper;
=======
    AccessRequestMapper accessRequestMapper;

    @Autowired
    CloneDetectionResultMapper cloneDetectionResultMapper;

>>>>>>> clonedetection-ef

    @ApiOperation("Validation check against possible clone card -  JSON Payload")
    @RequestMapping(value = "/request", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<CloneDetectionResultDto> getValidation(@Valid RfidPanelAccessRequestDto requestDto)
    {
<<<<<<< HEAD
        AccessRequest<RfidBadge, RfidReaderPanel> accessRequest = cloneDetectionResultMapper.dtoToDomain(requestDto);
=======
        CloneDetectionResultDto cloneDetectionResultDto=new CloneDetectionResultDto();

        AccessRequest<RfidBadge, RfidReaderPanel> accessRequest = accessRequestMapper.dtoToDomain(requestDto);

>>>>>>> clonedetection-ef
        CloneDetectionResult cloneValidationResult = cloneDetectionService.checkForClonedCard(accessRequest);
        CloneDetectionResultDto cloneDetectionResultDto = cloneDetectionResultMapper.domainToDto(cloneValidationResult);

        cloneDetectionService.setEventListener( (CloneDetectionResult cloneDetectionResult) -> {

            log.debug("Clone detection result payload for subscribed MQTT Listeners = {}", cloneDetectionResult);

            // ** John **
            // MQTT Publishing goes here
            //



            // ** Anna **
            // publishing results to web socket potentially?


        } );

        cloneDetectionResultDto = cloneDetectionResultMapper.domainToDto(cloneValidationResult);

        return new ResponseEntity<>(cloneDetectionResultDto, HttpStatus.OK);
    }

}
