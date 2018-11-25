package com.cit.restapi.rfidpanel.dto;

/**
 * Created by odziea on 11/18/2018.
 * adapted by efoy 23 Nov 2018
 */
public class MQTTCloneDetectionResultDto {

    public static final String IMPOSSIBLE_EVENT_TITLE = "Possible Cloned Access Card";
    public static final String POSSIBLE_EVENT_TITLE = "Regular access event. No issue found";

    public static final String IMPOSSIBLE_EVENT_DESC= "An access-card has been used that was very recently used in another location, indicating that it is unlikely to be the same card-holder";
    public static final String POSSIBLE_EVENT_DESC = "Regular access event. No issue found";

    private String severity;
    private AccessEventDto currentEvent;
    private AccessEventDto previousEvent;
    private String title;
    private String description;

    public MQTTCloneDetectionResultDto() {
    }

    public AccessEventDto getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(AccessEventDto currentEvent) {
        this.currentEvent = currentEvent;
    }

    public AccessEventDto getPreviousEvent() {
        return previousEvent;
    }

    public void setPreviousEvent(AccessEventDto previousEvent) {
        this.previousEvent = previousEvent;
    }

    public String isSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getSeverity() {
        return severity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
