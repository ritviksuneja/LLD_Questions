package com.example;

public class IllegalRatingException extends RuntimeException{
    public IllegalRatingException(String message) {
        super(message);
    }
}
