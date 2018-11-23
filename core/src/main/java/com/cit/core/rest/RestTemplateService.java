package com.cit.core.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * Created by odziea on 11/18/2018.
 */
public abstract class RestTemplateService implements IRestTemplateService {

    private RestTemplate restTemplate = new RestTemplate();

    protected Object getForObject(String endpoint, Class clazz){

        String uri = this.getRootUri() + endpoint;
        return this.restTemplate.getForObject(uri, clazz);
    }
}
