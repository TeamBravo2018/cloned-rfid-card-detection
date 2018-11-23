package com.cit.clonedetection.services;

import com.cit.clonedetection.model.Location;


public interface ILocatorService {
    Location getLocationFromPanelId(String panelId);
}
