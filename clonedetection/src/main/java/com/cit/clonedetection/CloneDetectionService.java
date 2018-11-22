package com.cit.clonedetection;

import com.cit.clonedetection.exceptions.CardIdException;
import com.cit.clonedetection.exceptions.PanelIdException;
import com.cit.clonedetection.mapper.CloneDetectionResultMapper;
import com.cit.clonedetection.model.Event;
import com.cit.clonedetection.model.Location;
import com.cit.clonedetection.services.IEventStoreService;
import com.cit.clonedetection.services.ILocatorService;
import com.cit.clonedetection.services.IValidationService;
import com.cit.clonedetection.transfer.ValidationServiceRestResponseDTO;
import com.cit.common.om.access.request.AccessRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Created by odziea on 11/12/2018.
 *
 * efoy 11/22/2018
 *    - integrated validation detection services and various rules engines
 *    - created publish subscribe listener
 */
@Slf4j
@Service
public class CloneDetectionService implements ICloneDetectionService{

    private ILocatorService locatorService;
    private IEventStoreService eventStoreService;
    private IValidationService validationService;
    private CloneDetectionServiceResultEvent listener =  null;


    @Autowired
    public CloneDetectionService(ILocatorService locatorService, IEventStoreService eventStoreService, IValidationService validationService ) {
        this.locatorService=locatorService;
        this.eventStoreService=eventStoreService;
        this.validationService=validationService;
    }

    public void setEventListener(CloneDetectionServiceResultEvent listener) {
        this.listener = listener;
    }

    public void publishDetectionEvent(CloneDetectionResult cloneDetectionResult) {
        if ( this.listener!=null ) {
            this.listener.detectionResult(cloneDetectionResult);
        }
    }

    @Override
    public CloneDetectionResult checkForClonedCard(AccessRequest accessRequest) {

        String panelId = accessRequest.getAccessIssuer().getId();
        String cardId  = accessRequest.getAccessToken().getTokenId();
        boolean allowed = accessRequest.isRequestGranted();
        long time = accessRequest.getAccessTime().toEpochSecond();

        // log the request
        if (log.isDebugEnabled()) {
            log.debug("Validation Request Parameters, panelId={}, cardId={}, allowed={}, timestamp={}", panelId, cardId, allowed,time);
        }

        // throw exception if bad parameters
        validateRequestParameters(panelId, cardId);

        // Find the location associated with the panel
        Location locationOfPanel = locatorService.getLocationFromPanelId(panelId);

        // Create new event object based on request
        Event currentEvent = Event.builder()
                .panelId(panelId)
                .cardId(cardId)
                .location(locationOfPanel)
                .accessAllowed(allowed)
                .timestamp(time)
                .build();

        // Find last event for card
        Event previousEvent = eventStoreService.getLastEventForCardId(cardId);

        // Perform validation checks and response DTO
        ValidationServiceRestResponseDTO response  = validationService.performEventValidation(currentEvent,previousEvent);

        // Finally store current event in cache so we can find it the next time the card is used
        eventStoreService.storeEvent(currentEvent);

        // build the result
        CloneDetectionResult cloneDetectionResult = CloneDetectionResultMapper.toCloneDetectionResult( currentEvent, previousEvent,  response  );

        // publish the event to subscriber
        publishDetectionEvent(cloneDetectionResult);

        return cloneDetectionResult;
    }

    private void validateRequestParameters(String panelId, String cardId ) {

        // make sure its a valid panel UUID
        try{
            UUID.fromString(panelId);
        } catch (IllegalArgumentException exception){
            //handle the case where string is not valid UUID
            throw new PanelIdException("Bad format panelId="+panelId);
        }

        // make sure its a valid card UUID
        try{
            UUID.fromString(cardId);
        } catch (IllegalArgumentException exception){
            // handle the case where string is not valid UUID
            throw new CardIdException("Bad format cardId=" + cardId);
        }
    }


}
