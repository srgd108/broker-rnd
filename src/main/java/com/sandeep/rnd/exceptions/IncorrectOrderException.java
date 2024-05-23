package com.sandeep.rnd.exceptions;

import org.springframework.http.HttpStatus;

public class IncorrectOrderException extends RuntimeException {
    private final HttpStatus errorCode;

    public IncorrectOrderException(HttpStatus errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public IncorrectOrderException(HttpStatus errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }
}
