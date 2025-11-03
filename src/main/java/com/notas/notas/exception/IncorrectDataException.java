package com.notas.notas.exception;

public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException(String message) {
        super("The data sent was incorrect: \n" + message);
    }
}
