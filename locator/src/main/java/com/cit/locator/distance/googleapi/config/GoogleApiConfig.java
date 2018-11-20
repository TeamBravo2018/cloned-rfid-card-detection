package com.cit.locator.distance.googleapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by odziea on 11/20/2018.
 */
@Component
@ConfigurationProperties(prefix = "googleapi")
@PropertySource("classpath:googleapi.properties")
public class GoogleApiConfig {

    private String key;
    private String baseUri;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }
}
