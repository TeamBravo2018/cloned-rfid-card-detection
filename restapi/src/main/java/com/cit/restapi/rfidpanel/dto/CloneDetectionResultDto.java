package com.cit.restapi.rfidpanel.dto;

/**
 * Created by odziea on 11/12/2018.
 */
public class CloneDetectionResultDto {

    private boolean allowAccess;
    private boolean validEvent;
    private String reason;
    private AccessEventDto currentEvent;
    private AccessEventDto previousEvent;

    public CloneDetectionResultDto() {
    }

    public boolean isValidEvent() {
        return validEvent;
    }

    public void setValidEvent(boolean validEvent) {
        this.validEvent = validEvent;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public boolean isAllowAccess() {
        return allowAccess;
    }

    public void setAllowAccess(boolean allowAccess) {
        this.allowAccess = allowAccess;
    }



}
