package com.cit.clonedetection.services;


import com.cit.clonedetection.model.Distance;
import com.cit.clonedetection.model.Location;

public interface IDistanceService {
    enum Mode{ walking, driving, flyDrive };
    Distance execute(Location current, Location previous, Mode mode );
}
