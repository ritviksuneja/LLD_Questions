package com.example;

public class AgentNotFoundException extends RuntimeException{
    public AgentNotFoundException(String message){
        super(message);
    }
}
