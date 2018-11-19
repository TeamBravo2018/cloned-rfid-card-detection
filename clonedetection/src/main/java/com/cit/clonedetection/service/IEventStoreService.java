package com.cit.clonedetection.service;

import com.cit.common.om.access.request.AccessRequest;

public interface IEventStoreService {

    AccessRequest getLastEventForCardId(String cardId);
    void storeEvent(AccessRequest event);

}
