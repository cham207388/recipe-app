package com.abc.recipemainservice.exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static com.abc.recipemainservice.exception.DateFormatter.dateTimeFormatter;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public final ResponseEntity<ExceptionResponse> generalExceptions(Exception ex) {
        ExceptionResponse exceptionResponse = exceptionResponse(ex.getCause().getMessage(), "General Exception");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ExceptionResponse numberFormatException(NumberFormatException ex) {
        return exceptionResponse(ex.getCause().getMessage(), "Requires a number and not a string");
    }

    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ExceptionResponse backRequest(BadRequestException ex) {
        return exceptionResponse(ex.getMessage(), "Bad request");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = RecipeNotFoundException.class)
    public final ExceptionResponse recipeNotFoundException(RecipeNotFoundException ex) {
        return exceptionResponse(ex.getMessage(), "Recipe not found!");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ServerNotFoundException.class)
    public final ExceptionResponse serverException(ServerNotFoundException ex){
        return exceptionResponse(ex.getMessage(), "Feign Exception");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ResponseStatusException.class)
    public final ExceptionResponse feignException(ResponseStatusException ex){
        return exceptionResponse(ex.getMessage(), ex.getReason());
    }

    private ExceptionResponse exceptionResponse(String message, String description){
        return ExceptionResponse.builder()
                .message(message)
                .description(description)
                .timeStamp(LocalDateTime.now().format(dateTimeFormatter))
                .build();
    }
}
