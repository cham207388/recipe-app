package com.abc.recipemainservice.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String reason) {
        super(reason);
    }
}
