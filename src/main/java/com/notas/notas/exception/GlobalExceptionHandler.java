package com.notas.notas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoteNotFoundException.class)
    public ModelAndView handleNoteNotFound(NoteNotFoundException ex){
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorResponse error = new ErrorResponse(status.value(), ex.getMessage());

        ModelAndView mav = new ModelAndView("errorView");
        mav.addObject("error", error);
        return mav;
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ModelAndView handleIncorrectData(IncorrectDataException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorResponse error = new ErrorResponse(status.value(), ex.getMessage());

        ModelAndView mav = new ModelAndView("errorView");
        mav.addObject("error", error);
        return mav;
    }
}
