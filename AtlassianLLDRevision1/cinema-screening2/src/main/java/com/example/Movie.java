package com.example;

public class Movie {
    private final String name;
    private final int duration;

    public Movie(String name, int duration) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("The movie's name can't be empty.");
        }
        if(duration < 0){
            throw new IllegalArgumentException("The duration of a movie can't be negative.");
        }
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}