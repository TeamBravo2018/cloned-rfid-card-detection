package com.cit.clonedetection;

import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.common.om.access.token.RfidBadge;
import com.cit.exceptions.CardIdException;
import com.cit.exceptions.PanelIdException;
import com.cit.model.Event;
import com.cit.model.Location;
import com.cit.services.IEventStoreService;
import com.cit.services.ILocatorService;
import com.cit.services.IValidationService;
import com.cit.transfer.ValidationServiceRestResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;

/**
 * Created by odziea on 11/12/2018.
 */
@Slf4j
@Service
public class CloneDetectionService implements ICloneDetectionService{

    private ILocatorService locatorService;
    private IEventStoreService eventStoreService;
    private IValidationService validationService;



    @Autowired
    public CloneDetectionService(ILocatorService locatorService, IEventStoreService eventStoreService, IValidationService validationService ) {
        this.locatorService=locatorService;
        this.eventStoreService=eventStoreService;
        this.validationService=validationService;
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
        CloneDetectionResult cloneDetectionResult = toCloneDetectionResult( currentEvent, previousEvent,  response  );

        return cloneDetectionResult;
    }

    private CloneDetectionResult toCloneDetectionResult( Event currentEvent, Event previousEvent,  ValidationServiceRestResponseDTO response  ) {

        AccessRequest<RfidBadge, RfidReaderPanel> currentAccessRequest = new AccessRequest<>();
        currentAccessRequest.setAccessIssuer(new RfidReaderPanel(currentEvent.getPanelId()));
        currentAccessRequest.setAccessToken(new RfidBadge(currentEvent.getCardId()));
        currentAccessRequest.setAccessTime(Instant.ofEpochMilli(currentEvent.getTimestamp()).atZone(ZoneOffset.UTC));
        currentAccessRequest.setRequestGranted(currentEvent.isAccessAllowed());

        AccessRequest<RfidBadge, RfidReaderPanel> previousAccessRequest = new AccessRequest<>();
        currentAccessRequest.setAccessIssuer(new RfidReaderPanel(previousEvent.getPanelId()));
        currentAccessRequest.setAccessToken(new RfidBadge(previousEvent.getCardId()));
        currentAccessRequest.setAccessTime(  Instant.ofEpochMilli(previousEvent.getTimestamp()).atZone(ZoneOffset.UTC));
        currentAccessRequest.setRequestGranted(previousEvent.isAccessAllowed());

        CloneDetectionResult cloneDetectionResult =  new CloneDetectionResult();
        cloneDetectionResult.setAccessRequest(currentAccessRequest);
        cloneDetectionResult.setPreviousAccessRequest(previousAccessRequest);
        cloneDetectionResult.setGenuineCard(response.isValidEvent());
        cloneDetectionResult.setReason(response.getReason());
        cloneDetectionResult.setCurrentLocation(currentEvent.getLocation());
        cloneDetectionResult.setPreviousLocation(previousEvent.getLocation());

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
