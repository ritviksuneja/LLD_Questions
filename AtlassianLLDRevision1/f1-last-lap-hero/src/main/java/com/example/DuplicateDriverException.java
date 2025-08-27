package com.example;

public class DuplicateDriverException extends RuntimeException{
    public DuplicateDriverException(String message){
        super(message);
    }
}
