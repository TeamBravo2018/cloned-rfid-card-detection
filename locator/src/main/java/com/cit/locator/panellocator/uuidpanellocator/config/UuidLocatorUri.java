package com.cit.locator.panellocator.uuidpanellocator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by odziea on 11/18/2018.
 */
@Component
@ConfigurationProperties(prefix = "uuilocator")
@PropertySource("classpath:uuidlocator.properties")
public class UuidLocatorUri {

    private String root;
    private String panels;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getPanels() {
        return panels;
    }

    public void setPanels(String panels) {
        this.panels = panels;
    }
}
