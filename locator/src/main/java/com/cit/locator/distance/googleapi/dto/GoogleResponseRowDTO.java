package com.cit.locator.distance.googleapi.dto;

import java.util.List;

/**
 * Created by odziea on 11/20/2018.
 */
public class GoogleResponseRowDTO {

    private List<GoogleResponseElementDTO> elements;

    public List<GoogleResponseElementDTO> getElements() {
        return elements;
    }

    public void setElements(List<GoogleResponseElementDTO> elements) {
        this.elements = elements;
    }
}
