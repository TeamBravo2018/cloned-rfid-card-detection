package com.cit.services;

import com.cit.model.Location;


public interface ILocatorService {
    Location getLocationFromPanelId(String panelId);
}
