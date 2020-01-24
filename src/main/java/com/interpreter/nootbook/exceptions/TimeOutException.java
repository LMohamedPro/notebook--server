package com.interpreter.nootbook.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Request taking too long to execute")
public class TimeOutException extends RuntimeException {

    public TimeOutException(String message) {
        super(message);
    }
}
