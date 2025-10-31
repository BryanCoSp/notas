package com.notas.notas.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(long id) {
        super("No se encontró la nota con el id " + id);
    }
}
