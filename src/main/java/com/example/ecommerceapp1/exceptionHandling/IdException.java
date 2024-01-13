package com.example.ecommerceapp1.exceptionHandling;

public class IdException extends Exception{
    private String errorMessage;


    public IdException(){}

    public IdException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
