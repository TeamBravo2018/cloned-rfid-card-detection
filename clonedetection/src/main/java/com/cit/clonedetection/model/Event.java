package com.cit.clonedetection.model;

@lombok.Data
@lombok.Builder
public class Event {
    private long timestamp;
    private String cardId;
    private String panelId;
    private Location location;
    private boolean accessAllowed;
}

