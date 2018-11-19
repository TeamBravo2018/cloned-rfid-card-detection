package com.cit.clonedetection.service;

import com.cit.clonedetection.service.IValidationService;
import com.cit.clonedetection.transfer.ValidationServiceRestResponseDTO;
import com.cit.clonedetection.rules.EventValidationBean;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.clonedetection.CloneDetectionResult;
import com.cit.clonedetection.rules.IDistanceService;
import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ValidationService implements IValidationService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private RuleBook eventRuleBook;
    private IDistanceService distanceService;


    @Autowired
    public ValidationService(RuleBook eventRuleBook, IDistanceService distanceService) {
        this.eventRuleBook=eventRuleBook;
        this.distanceService=distanceService;
    }



    @Override
    public ValidationServiceRestResponseDTO performEventValidation(AccessRequest current, AccessRequest previous) {


        // default to possible result
        CloneDetectionResult validationRulesResult = CloneDetectionResult.builder()
                .reason("Possible time-distance event.")
                .genuineCard(true)
                .build();

        if (log.isDebugEnabled()) {
            log.debug("PerformEventValidation(CurrentEvent={} , PreviousEvent={})", current, previous );
        }

        if (previous != null) {

            NameValueReferableMap facts = new FactMap();
            facts.setValue("eventValidation", new EventValidationBean(current,previous,distanceService));

            eventRuleBook.run(facts);

            Optional<Result> optionalResult = eventRuleBook.getResult();
            if (optionalResult.isPresent()) {

                CloneDetectionResult result = (CloneDetectionResult) optionalResult.get().getValue();
                validationRulesResult.setReason(result.getReason());
                validationRulesResult.setGenuineCard(result.isGenuineCard());
            }

        } else {  // must be a valid Event
            if (log.isDebugEnabled()) {
                log.debug("Can not find previous event for cardId={} , Event={}" ,current.getAccessToken().getTokenId(), current );
            }
        }

        //
        // Build the response DTO
        //
        return ValidationServiceRestResponseDTO.builder()
                .currentEvent(current)
                .previousEvent(previous)
                .reason(validationRulesResult.getReason())
                .validEvent(validationRulesResult.isGenuineCard())
                .build();

    }
}
