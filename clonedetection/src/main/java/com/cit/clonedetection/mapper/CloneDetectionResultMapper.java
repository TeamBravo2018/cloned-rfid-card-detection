package com.cit.clonedetection.mapper;

import com.cit.clonedetection.CloneDetectionResult;
import com.cit.clonedetection.model.Event;
import com.cit.clonedetection.transfer.ValidationServiceRestResponseDTO;
import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.common.om.access.token.RfidBadge;
import com.cit.common.om.location.Address;
import com.cit.common.om.location.Building;
import com.cit.common.om.location.GeoLocation;

import java.time.Instant;
import java.time.ZoneOffset;

public class CloneDetectionResultMapper {


    public static AccessRequest<RfidBadge, RfidReaderPanel> toAccessRequest(Event event ) {

        if(event == null)
            return null;

        AccessRequest<RfidBadge, RfidReaderPanel> accessRequest = new AccessRequest<>();


        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setX(event.getLocation().getCoordinates().getLongitude());
        geoLocation.setY(event.getLocation().getCoordinates().getLatitude());
        geoLocation.setZ(event.getLocation().getAltitude());

        String [] addressField = event.getLocation().getRelativeLocation().split(",");

        Address address  = new Address();
        address.setAddressLine1(addressField[0]);
        address.setAddressLine2("");
        address.setCity(addressField[1]);
        address.setCountry(addressField[2]);

        Building building = new Building();
        building.setName(event.getLocation().getRelativeLocation());
        building.setAddress(address);
        building.setCoordinates(geoLocation);

        RfidBadge badge =  new RfidBadge();
        badge.setBuilding(building);
        badge.setTokenId(event.getCardId());

        RfidReaderPanel panel  = new RfidReaderPanel(event.getPanelId());
        panel.setGeoLocation(geoLocation);
        panel.setBuilding(building);

        accessRequest.setAccessIssuer(panel);
        accessRequest.setAccessToken(badge);
        accessRequest.setAccessTime(Instant.ofEpochMilli(event.getTimestamp()).atZone(ZoneOffset.UTC));
        accessRequest.setRequestGranted(event.isAccessAllowed());
        accessRequest.setAccessIssuer(panel);

        return accessRequest;

    }

    public static CloneDetectionResult toCloneDetectionResult(Event currentEvent, Event previousEvent, ValidationServiceRestResponseDTO response  ) {

        AccessRequest<RfidBadge, RfidReaderPanel> currentAccessRequest = toAccessRequest( currentEvent );

        AccessRequest<RfidBadge, RfidReaderPanel> previousAccessRequest = toAccessRequest( previousEvent );

        CloneDetectionResult cloneDetectionResult =  new CloneDetectionResult();
        cloneDetectionResult.setAccessRequest(currentAccessRequest);
        cloneDetectionResult.setPreviousAccessRequest(previousAccessRequest);
        cloneDetectionResult.setGenuineCard(response.isValidEvent());
        cloneDetectionResult.setReason(response.getReason());
        return cloneDetectionResult;
    }

}
