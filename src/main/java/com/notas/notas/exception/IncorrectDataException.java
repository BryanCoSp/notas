package com.notas.notas.exception;

public class IncorrectDataException extends RuntimeException {
    public IncorrectDataException(String message) {
        super("Los datos enviados no son correctos: \n" + message);
    }
}
