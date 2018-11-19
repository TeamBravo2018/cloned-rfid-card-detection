package com.cit.clonedetection.rules;

import com.cit.clonedetection.models.Distance;
import com.cit.common.om.location.GeoLocation;

public interface IDistanceService {
    enum Mode{ walking, driving, flyDrive };
    Distance execute(GeoLocation current, GeoLocation previous, Mode mode );
}
