package com.example;

public class Screening {
    private final Movie movie;
    private final int startTime;

    public Screening(Movie movie, int startTime) {
        if(movie == null){
            throw new IllegalArgumentException("movie can't be null.");
        }
        if(startTime < 0){
            throw new IllegalArgumentException("startTime can't be less than 0.");
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

    public int getEndTIme(){
        return startTime + movie.getDuration();
    }
}
