package com.abc.recipemainservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static com.abc.recipemainservice.exception.DateFormatter.*;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(ex.getCause().getMessage())
                .description("General exception")
                .timeStamp(LocalDateTime.now().format(dateTimeFormatter))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ExceptionResponse> handleNumberFormatRecipeException(Exception ex){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(ex.getCause().getMessage())
                .description("Requires a number and not a string")
                .timeStamp(LocalDateTime.now().format(dateTimeFormatter))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = RecipeNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> recipeNotFoundException(Exception ex){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(ex.getMessage())
                .description("Recipe not found")
                .timeStamp(LocalDateTime.now().format(dateTimeFormatter))
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
