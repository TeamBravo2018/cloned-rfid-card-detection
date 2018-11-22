package com.cit.clonedetection.services;

import com.cit.Helper;
import com.cit.clonedetection.model.Distance;
import com.cit.clonedetection.model.GPSCoordinate;
import com.cit.clonedetection.model.Location;
import com.cit.clonedetection.config.ServicesConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ContextConfiguration(classes = ServicesConfig.class)
class GoogleDistanceServiceTest {

    String apikey="GOOGLEKEY";
    RestTemplate restTemplate =  new RestTemplate();
    private MockRestServiceServer mockServer;
    GoogleDistanceService googleDistanceService;

    Location CITWest;
    Location CITNorth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        // setup test data
        CITWest = Location.builder()
                .coordinates(GPSCoordinate.builder()
                        .latitude(51.884827)
                        .longitude(-8.533947)
                        .build())
                .altitude(100)
                .relativeLocation("CIT Library West Wing Entry Doors, Cork, Ireland")
                .build();

        CITNorth = Location.builder()
                .coordinates(GPSCoordinate.builder()
                        .latitude(51.884969)
                        .longitude(-8.533235)
                        .build())
                .altitude(100)
                .relativeLocation("CIT Library North Ground Exit, Cork, Ireland")
                .build();

        RestGatewaySupport gateway = new RestGatewaySupport();
        gateway.setRestTemplate(restTemplate);

        mockServer = MockRestServiceServer.createServer(gateway);

        googleDistanceService = new GoogleDistanceService(restTemplate,apikey);
    }

    @Test
    void testSGoogleDistanceService_walking() throws IOException {

        mockServer.expect(once(), requestTo(googleDistanceService.getRequestURL(CITWest, CITNorth, GoogleDistanceService.Mode.walking)))
                .andRespond(withSuccess(Helper.getGoogleJson("mockData/google.json"), MediaType.APPLICATION_JSON));

        Distance responseDTO = googleDistanceService.execute(CITWest, CITNorth, IDistanceService.Mode.walking);

        System.out.println(responseDTO);
        assertEquals(true, responseDTO.getStatus().equals("OK"));
    }

    @Test
    void testSGoogleDistanceService_driving() throws IOException {

        mockServer.expect(once(), requestTo(googleDistanceService.getRequestURL(CITWest, CITNorth, GoogleDistanceService.Mode.driving)))
                .andRespond(withSuccess(Helper.getGoogleJson("mockData/google.json"), MediaType.APPLICATION_JSON));

        Distance responseDTO =  responseDTO = googleDistanceService.execute(CITWest, CITNorth, IDistanceService.Mode.driving);

        System.out.println(responseDTO);
        assertEquals(true, responseDTO.getStatus().equals("OK"));
    }


}

