package com.abc.recipemainservice.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String reason) {
        super(reason);
    }
}
