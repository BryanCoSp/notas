package com.notas.notas.exception;

public class ServiceTechnicalException extends RuntimeException {
    public ServiceTechnicalException(String message) {
        super("The service is having problems, please try again later: " + message);
    }
}
