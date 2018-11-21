package com.cit.restapi.rfidpanel;

import com.cit.clonedetection.CloneDetectionResult;
import com.cit.clonedetection.ICloneDetectionService;
import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.common.om.access.token.RfidBadge;
import com.cit.restapi.rfidpanel.dto.CloneDetectionResultDto;
import com.cit.restapi.rfidpanel.dto.RfidPanelAccessRequestDto;
import com.cit.restapi.rfidpanel.mapper.AccessRequestMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "api/panels/request", description = "RFID panel requests")
@RestController
@RequestMapping(value = "api/panels", produces = "application/json", consumes = "application/json")
public class RfidPanelResource {

    @Autowired
    ICloneDetectionService cloneDetectionService;

    @Autowired
    AccessRequestMapper accessRequestMapper;

//    @Autowired
//    CloneDetectionResultMapper cloneDetectionResultMapper;


    @ApiOperation("Validation check against possible clone card")
    @RequestMapping(value = "/request", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<CloneDetectionResultDto> getValidation(@Valid RfidPanelAccessRequestDto requestDto)
    {
        CloneDetectionResultDto cloneDetectionResultDto=new CloneDetectionResultDto();

        AccessRequest<RfidBadge, RfidReaderPanel> accessRequest = accessRequestMapper.dtoToDomain(requestDto);


        CloneDetectionResult cloneValidationResult = cloneDetectionService.checkForClonedCard(accessRequest);



        return new ResponseEntity<>(cloneDetectionResultDto, HttpStatus.OK);
    }
}
