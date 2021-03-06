package com.cit.clonedetection.config;


import com.cit.clonedetection.CloneDetectionService;
import com.cit.clonedetection.rules.EventValidationRuleBook;
import com.cit.clonedetection.services.*;
import com.cit.clonedetection.transfer.ValidationRulesResult;
import com.deliveredtechnologies.rulebook.lang.RuleBookBuilder;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ServicesConfig {

    @Value("${google.api-key}")
    private String apiKey;


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    @Primary
    public LocatorService locatorService() {
        return new LocatorService();
    }

    @Bean
    @Primary
    ValidationService validationService() {
        return new ValidationService(eventRuleBook(),distanceService());
    }

    @Bean
    CloneDetectionService cloneDetectionService() { return new CloneDetectionService(locatorService(),eventStoreService(),validationService()); }


    @Bean
    ValidationService eventValidationService(RuleBook  eventRuleBook, IDistanceService distanceService) {
        return new ValidationService(eventRuleBook, distanceService);
    }

    @Bean
    @Primary
    IEventStoreService eventService() {
        return new EventStoreService();
    }

    @Bean
    RuleBook  eventRuleBook() {

        return RuleBookBuilder.create(EventValidationRuleBook.class).withResultType(ValidationRulesResult.class)
                .withDefaultResult(ValidationRulesResult.builder().reason("Possible time-distance event").validEvent(true).build())
                .build();
    }

    @Bean
    IDistanceService distanceService() {return new DistanceFacadeService(googleDistanceService(),localDistanceService(), flyAndDriveDistanceService());}

    @Bean
    FlyAndDriveDistanceService flyAndDriveDistanceService() { return new FlyAndDriveDistanceService();}

    @Bean
    LocalDistanceService localDistanceService() { return new LocalDistanceService(); }

    @Bean
    GoogleDistanceService googleDistanceService() {
        return new GoogleDistanceService(restTemplate(),apiKey);
    }

    @Bean
    EventStoreService eventStoreService() {
        return new EventStoreService();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }



}
