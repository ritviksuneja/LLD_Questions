package com.example;

public class DuplicateSubscriptionException extends RuntimeException{
    public DuplicateSubscriptionException(String message){
        super(message);
    }
}
