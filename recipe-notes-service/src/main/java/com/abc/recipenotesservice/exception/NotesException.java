package com.abc.recipenotesservice.exception;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.abc.recipenotesservice.utils.DateStringConverter.DATE_TIME_FORMATTER;

@Getter
@Setter
public class NotesException extends RuntimeException {
    private String message;
    private String dateTime;

    public NotesException() {
        this.message = super.getMessage();
        this.dateTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }
}
