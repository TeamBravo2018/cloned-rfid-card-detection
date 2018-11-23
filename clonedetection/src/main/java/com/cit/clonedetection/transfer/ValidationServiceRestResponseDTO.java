package com.cit.clonedetection.transfer;


import com.cit.clonedetection.model.Event;

@lombok.Data
@lombok.Builder
public class ValidationServiceRestResponseDTO {
    private String reason;
    private Event currentEvent;
    private Event previousEvent;
    private boolean validEvent;
}
