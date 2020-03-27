package com.abc.recipenotesservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumberFormatNotesException {
    private String message;
    private String localDateTime;
}
