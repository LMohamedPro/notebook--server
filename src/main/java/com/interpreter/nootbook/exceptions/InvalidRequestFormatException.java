package com.interpreter.nootbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid Request Format")
public class InvalidRequestFormatException extends RuntimeException {

    public InvalidRequestFormatException(String message) {
        super(message);
    }
}
