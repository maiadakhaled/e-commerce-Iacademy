package com.example.e_commerce.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> invalidArgumentHandler(MethodArgumentNotValidException ex) {
        return exceptionHandler(ex,HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"Argument not valid");
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> illegalArgumentHandler(IllegalArgumentException ex) {
        return exceptionHandler(ex,HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value(),"IllegalArgumentException");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> runTimeExceptionHandler(RuntimeException ex){
        return exceptionHandler(ex,HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
    }


    @ExceptionHandler(CustomExceptions.class)
    public ResponseEntity<ExceptionResponse> customExceptionHandler(CustomExceptions ex) {
        String message = messageSource.getMessage(ex.getMessage(), null, Locale.ENGLISH);
        return new ResponseEntity<ExceptionResponse>(
                new ExceptionResponse(message, ex.getStatus(), ex.getStatus().value(), LocalDateTime.now()),
                ex.getStatus());
    }


    private ResponseEntity<ExceptionResponse> exceptionHandler(Exception ex, HttpStatus status, int code, String message) {
        String msg;
            try {
                msg = messageSource.getMessage(message, null, Locale.ENGLISH);
            }
            catch (Exception exception) {
                System.err.println("Couldn't find the message in resources");
                msg = message;
            }
        return new ResponseEntity<ExceptionResponse>(
                new ExceptionResponse(msg, status, code, LocalDateTime.now()), status);
    }

}
