package com.cit.locator.distance.googleapi;

import com.cit.common.om.location.GeoLocation;
import com.cit.core.rest.RestTemplateService;
import com.cit.locator.distance.googleapi.config.GoogleApiConfig;
import com.cit.locator.distance.googleapi.dto.GoogleResponseDTO;
import com.cit.locator.distance.googleapi.mapper.GoogleApiMapper;
import com.cit.locator.distance.om.Distance;
import com.cit.locator.distance.om.TravelMeans;
import com.cit.locator.distance.service.IDistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleApiService extends RestTemplateService implements IGoogleApiService {

    @Autowired
    private GoogleApiConfig googleApiConfig;

    @Autowired
    private GoogleApiMapper googleApiMapper;

    public String getRootUri(){
        return this.googleApiConfig.getBaseUri();
    }

    public String getRequestURL(GeoLocation current, GeoLocation previous, TravelMeans mode) {
        String origin = String.format("%s,%s",current.getX(),current.getX());
        String destination = String.format("%s,%s",previous.getY(),previous.getY());
        return String.format("&origins=%s&destinations=%s&key=%s&mode=%s", origin,destination, this.googleApiConfig.getKey(), mode.toString());
    }


    public Distance execute(GeoLocation current, GeoLocation previous, TravelMeans travelMeans ) {

        if(this.googleApiConfig.getKey()==null)
            throw new IllegalArgumentException("GoogleDistanceService apiKey not set");


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        GoogleResponseDTO result = (GoogleResponseDTO)this.getForObject(this.getRequestURL(current, previous, travelMeans), GoogleResponseDTO.class);

        Distance distance = new Distance();

        if(result.getStatus().equalsIgnoreCase("OK") && !result.getRows().get(0).getElements().get(0).getStatus().equalsIgnoreCase("ZERO_RESULTS")){
            distance = googleApiMapper.googleResponseDtoToDistance(result);
        }else if(result.getStatus().equals("REQUEST_DENIED")){
            throw new IllegalArgumentException("GoogleDistanceService REQUEST_DENIED ");

        }
        return distance;

    }

}