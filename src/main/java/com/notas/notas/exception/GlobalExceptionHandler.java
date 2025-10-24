package com.notas.notas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoteNotFound(NoteNotFoundException ex){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse error = new ErrorResponse(status.value(), ex.getMessage());
        return new ResponseEntity<>(error, status);
    }
}
