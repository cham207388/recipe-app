package com.abc.recipemainservice.exception;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
