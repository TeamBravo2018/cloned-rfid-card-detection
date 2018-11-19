package com.cit.clonedetection.service;

import com.cit.clonedetection.models.Distance;
import com.cit.clonedetection.rules.IDistanceService;
import org.springframework.context.annotation.Bean;
import com.cit.clonedetection.transfer.GoogleResponseDTO;
import com.cit.common.om.location.GeoLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

@Configuration
@Service
@Slf4j
public class GoogleApiService implements IDistanceService {
    private String apiKey = "AIzaSyBkzAoC6KtzmNl0ZSIyLZEq8Vr8jhRkItI";
    private RestTemplate restTemplate;

    @Autowired
    public GoogleApiService(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
        //this.apiKey=apiKey;
    }

    public String getRequestURL(GeoLocation current, GeoLocation previous, Mode mode) {
        return getRequestURL(apiKey, current, previous, mode);
    }

    public String getRequestURL(String key, GeoLocation current, GeoLocation previous, Mode mode) {
        String baseUri = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric";
        String origin = String.format("%s,%s",current.getX(),current.getX());
        String destination = String.format("%s,%s",previous.getY(),previous.getY());
        System.out.println("feck");
        return String.format("%s&origins=%s&destinations=%s&key=%s&mode=%s", baseUri,origin,destination, key, mode.toString());
    }


    public Distance execute(GeoLocation current, GeoLocation previous, Mode mode ) {

        Distance distance = Distance.builder()
                .distance(0)
                .duration(0)
                .mode(mode.toString())
                .status("ERROR")
                .build();

        if(apiKey==null)
            throw new IllegalArgumentException("GoogleDistanceService apiKey not set");


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<GoogleResponseDTO> result = restTemplate.exchange(getRequestURL(apiKey,current,previous,mode), HttpMethod.GET, entity, GoogleResponseDTO.class);
        if( result.getStatusCode().is2xxSuccessful() ) {

            GoogleResponseDTO googleResult =  result.getBody();

            assert googleResult != null;

            String status = googleResult.getStatus();
            if(status.equals("REQUEST_DENIED"))
                throw new IllegalArgumentException("GoogleDistanceService REQUEST_DENIED : " + result.getBody() );

            int dis = googleResult.getRows().get(0).getElements().get(0).getDistance().getValue();
            int dur = googleResult.getRows().get(0).getElements().get(0).getDuration().getValue();

            distance = Distance.builder()
                    .distance(dis)
                    .duration(dur)
                    .mode(mode.toString())
                    .status("OK")
                    .build();

        }

        return distance;

    }

}