package com.cit.services;

import com.cit.model.Event;

public interface IEventStoreService {

    Event getLastEventForCardId(String cardId);
    void storeEvent(Event event);

}
