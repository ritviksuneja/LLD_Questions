package com.example;

public class Screening {
    private final Movie movie;
    private final int startTime;

    public Screening(Movie movie, int startTime) {
        if(movie == null){
            throw new IllegalArgumentException("Movie value can't be null.");
        }

        this.movie = movie;
        this.startTime = startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime(){
        return startTime + movie.getDuration();
    }
}
