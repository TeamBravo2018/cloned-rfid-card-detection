package com.cit.locator.distance.googleapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by odziea on 11/20/2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoogleResponseValue {

      private String text;
      private int value;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
