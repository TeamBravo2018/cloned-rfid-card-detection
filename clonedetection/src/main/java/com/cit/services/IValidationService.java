package com.cit.services;


import com.cit.model.Event;
import com.cit.transfer.ValidationServiceRestResponseDTO;

public interface IValidationService {
    ValidationServiceRestResponseDTO performEventValidation(Event current, Event previous);
}
