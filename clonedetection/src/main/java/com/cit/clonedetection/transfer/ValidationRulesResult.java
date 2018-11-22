package com.cit.clonedetection.transfer;

@lombok.Data
@lombok.Builder
public class ValidationRulesResult {
    private boolean validEvent;
    private String reason;
}
