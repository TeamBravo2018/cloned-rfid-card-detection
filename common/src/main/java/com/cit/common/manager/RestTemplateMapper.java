package com.cit.common.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by odziea on 11/14/2018.
 */
@Service
public class RestTemplateMapper<T> implements IRestTemplateMapper<T> {

    private final RestTemplate restTemplate;

    @Autowired
    private IURILookup uriLookup;

    public RestTemplateMapper(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public T getForObject(Class<T> clazz, ApplicationType applicationType, String request){
        return restTemplate.getForObject(uriLookup.lookup(applicationType, request), clazz);
    }
}
