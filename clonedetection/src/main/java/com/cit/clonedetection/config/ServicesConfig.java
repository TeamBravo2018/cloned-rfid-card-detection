package com.cit.clonedetection.config;

import com.cit.clonedetection.CloneDetectionResult;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;
import com.cit.clonedetection.service.DistanceFacadeService;
import com.cit.clonedetection.service.GoogleApiService;
import com.cit.clonedetection.service.LocalDistanceService;
import com.cit.clonedetection.service.FlyAndDriveDistanceService;
import com.cit.clonedetection.rules.EventValidationRuleBook;
import com.cit.clonedetection.rules.IDistanceService;


@Configuration
public class ServicesConfig {

    @Value("${google.api-key}")
    private String apiKey;

    @Value("${uri.location.service.panels:http://localhost:8080/api/locations}")
    private String uriLocations = "http://localhost:8080/api/locations";


    @Bean
    RestTemplate restTemplate() { return new RestTemplate(); }

    @Bean
    RuleBook  eventRuleBook() {

        return RuleBookBuilder.create(EventValidationRuleBook.class).withResultType(CloneDetectionResult.class)
                .withDefaultResult(CloneDetectionResult.builder().reason("Possible time-distance event").genuineCard(true).build())
                .build();
    }

    @Bean
    IDistanceService distanceService() {return new DistanceFacadeService(GoogleApiService(),localDistanceService(), flyAndDriveDistanceService());}

    @Bean
    FlyAndDriveDistanceService flyAndDriveDistanceService() { return new FlyAndDriveDistanceService();}

    @Bean
    LocalDistanceService localDistanceService() { return new LocalDistanceService(); }

    @Bean
    GoogleApiService GoogleApiService() {
        return new GoogleApiService(restTemplate());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
