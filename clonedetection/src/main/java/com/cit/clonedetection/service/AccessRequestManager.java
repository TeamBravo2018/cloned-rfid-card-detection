package com.cit.clonedetection.service;

import com.cit.common.om.access.request.AccessRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by odziea on 11/19/2018.
 */
@Service
public class AccessRequestManager implements IAccessRequestManager{

    Map<String, AccessRequest> accessRequestRecord = new HashMap<>();

    public AccessRequest findPreviousAccessRequestForCard(String cardId){
        return accessRequestRecord.get(cardId);
    }

    public void recordAccessRequest(AccessRequest accessRequest){
        accessRequestRecord.put(accessRequest.getAccessToken().getTokenId(), accessRequest);
    }
}
