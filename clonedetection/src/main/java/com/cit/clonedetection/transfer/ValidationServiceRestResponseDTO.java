package com.cit.clonedetection.transfer;

import com.cit.common.om.access.request.AccessRequest;

@lombok.Data
@lombok.Builder
public class ValidationServiceRestResponseDTO {
    private String reason;
    private AccessRequest currentEvent;
    private AccessRequest previousEvent;
    private boolean validEvent;
}
