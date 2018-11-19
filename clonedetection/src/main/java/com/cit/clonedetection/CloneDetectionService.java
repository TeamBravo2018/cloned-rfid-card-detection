package com.cit.clonedetection;

import com.cit.common.om.access.request.AccessRequest;
import com.cit.clonedetection.rules.EventValidationRuleBook;
import lombok.Builder;
import org.springframework.stereotype.Service;

/**
 * Created by odziea on 11/12/2018.
 */
@Service
public class CloneDetectionService implements ICloneDetectionService{

    @Override
    public CloneDetectionResult checkForClonedCard(AccessRequest accessRequest) {
        return null;
    }
}
