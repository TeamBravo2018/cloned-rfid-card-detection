package com.cit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class PanelIdException extends RuntimeException {
    public PanelIdException(String exception) {
        super(exception);

    }
}
