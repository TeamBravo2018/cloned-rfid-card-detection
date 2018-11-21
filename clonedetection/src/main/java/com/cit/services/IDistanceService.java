package com.cit.services;


import com.cit.model.Distance;
import com.cit.model.Location;

public interface IDistanceService {
    enum Mode{ walking, driving, flyDrive };
    Distance execute(Location current, Location previous, Mode mode );
}
