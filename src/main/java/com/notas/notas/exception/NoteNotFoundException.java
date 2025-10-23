package com.notas.notas.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(long id) {
        super("No se encontro el registro con el id " + id);
    }
}
