package com.cit.clonedetection;

import com.cit.common.om.access.request.AccessRequest;
import org.springframework.stereotype.Service;

/**
 * Created by odziea on 11/12/2018.
 */

public interface ICloneDetectionService {

    CloneDetectionResult checkForClonedCard(AccessRequest accessRequest);
}
