package com.cit.clonedetection.service;

import com.cit.common.om.access.request.AccessRequest;

/**
 * Created by odziea on 11/19/2018.
 */
public interface IAccessRequestManager {

    AccessRequest findPreviousAccessRequestForCard(String cardId);

    public void recordAccessRequest(AccessRequest accessRequest);
}
