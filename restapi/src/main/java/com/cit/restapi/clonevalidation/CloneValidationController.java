package com.cit.restapi.clonevalidation;

import com.cit.clonedetection.ICloneDetectionService;
import com.cit.restapi.clonevalidation.dto.CloneValidationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "api/panels/request", description = "RFID panel requests")
@RestController
@RequestMapping("api/panels")
public class CloneValidationController {

    @Autowired
    ICloneDetectionService cloneDetectionService;

    @ApiOperation("Validation check against possible clone card")
    @RequestMapping(value = "/request", method = RequestMethod.PUT)
    @ResponseBody
    public CloneValidationResponse getValidation(@RequestParam("panelId") String panelId,
                                                 @RequestParam("cardId") String cardId,
                                                 @RequestParam("allowed") boolean allowed
    ){
//        cloneDetectionService.checkForClonedCard();
        return new CloneValidationResponse();
    }
}
