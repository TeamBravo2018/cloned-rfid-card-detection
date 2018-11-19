package com.cit.clonedetection.service;

import com.cit.common.om.access.request.AccessRequest;
import com.cit.clonedetection.transfer.ValidationServiceRestResponseDTO;

public interface IValidationService {
    ValidationServiceRestResponseDTO performEventValidation(AccessRequest current, AccessRequest previous);
}
