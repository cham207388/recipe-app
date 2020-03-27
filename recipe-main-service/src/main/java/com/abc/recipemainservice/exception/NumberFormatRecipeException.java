package com.abc.recipemainservice.exception;


public class NumberFormatRecipeException extends RuntimeException {

    public NumberFormatRecipeException(String errorMessage){
        super(errorMessage);
    }
}
