package com.notas.notas.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(long id) {
        super("The note with id " + id + " was not found");
    }
}
