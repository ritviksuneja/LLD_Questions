package com.example;

public class DuplicateRouteException extends RuntimeException{
    public DuplicateRouteException(String message){
        super(message);
    }
}
