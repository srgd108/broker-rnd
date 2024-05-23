package com.sandeep.rnd.exceptions;

import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(AppExceptionHandler.class);

    private ResponseEntity<Object> populateErrorDetails(Exception ex, HttpStatus status, WebRequest request) {
        var errorDetails = ErrorDetails.builder()
                .error(status.getReasonPhrase())
                .status(status.value())
                .path(((ServletWebRequest) request).getRequest().getRequestURI()).details(ex.getMessage()).timestamp(new Date()).build();
        return new ResponseEntity<>(errorDetails, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(WebRequest request, Exception ex) {
        LOG.error("Exception occurred: ", ex);
        return populateErrorDetails(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(IncorrectOrderException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(WebRequest request, IncorrectOrderException ex) {
        LOG.error("Incorrect Order Exception occurred: ", ex);
        return populateErrorDetails(ex, ex.getErrorCode(), request);
    }

    @Builder
    @Data
    private static class ErrorDetails {
        String error;
        Integer status;
        String path;
        String details;
        Date timestamp;
    }

}
