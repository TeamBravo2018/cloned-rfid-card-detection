package com.cit.clonedetection.services;

import com.cit.clonedetection.model.Event;

public interface IEventStoreService {

    Event getLastEventForCardId(String cardId);
    void storeEvent(Event event);

}
