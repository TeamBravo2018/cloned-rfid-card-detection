package com.cit.clonedetection;

import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.common.om.access.token.RfidBadge;

/**
 * Created by odziea on 11/12/2018.
 */
public class CloneDetectionResult {

    private boolean genuineCard;
    private AccessRequest<RfidBadge, RfidReaderPanel> accessRequest;
    private AccessRequest<RfidBadge, RfidReaderPanel> previousAccessRequest;
    private String reason;

    public CloneDetectionResult() {
    }

    public boolean isGenuineCard() {
        return genuineCard;
    }

    public void setGenuineCard(boolean genuineCard) {
        this.genuineCard = genuineCard;
    }

    public AccessRequest<RfidBadge, RfidReaderPanel> getAccessRequest() {
        return accessRequest;
    }

    public void setAccessRequest(AccessRequest<RfidBadge, RfidReaderPanel> accessRequest) {
        this.accessRequest = accessRequest;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AccessRequest<RfidBadge, RfidReaderPanel> getPreviousAccessRequest() {
        return previousAccessRequest;
    }

    public void setPreviousAccessRequest(AccessRequest<RfidBadge, RfidReaderPanel> previousAccessRequest) {
        this.previousAccessRequest = previousAccessRequest;
    }
}
