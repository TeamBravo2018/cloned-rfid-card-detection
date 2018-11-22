package com.cit.clonedetection.services;


import com.cit.clonedetection.model.Event;
import com.cit.clonedetection.transfer.ValidationServiceRestResponseDTO;

public interface IValidationService {
    ValidationServiceRestResponseDTO performEventValidation(Event current, Event previous);
}
