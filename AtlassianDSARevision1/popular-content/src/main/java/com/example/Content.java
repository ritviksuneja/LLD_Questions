package com.example;

public class Content {
    private final int id;
    private int popularity;

    public Content(int id) {
        this.id = id;
        this.popularity = 0;
    }

    public int getId() {
        return id;
    }

    public int getPopularity() {
        return popularity;
    }

    public void increasePopularity(){
        popularity++;
    }

    public void decreasePopularity(){
        popularity--;
    }
}
