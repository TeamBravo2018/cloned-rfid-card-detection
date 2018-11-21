package com.cit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class CardIdException extends RuntimeException {
    public CardIdException(String exception) {
        super(exception);

    }
}
