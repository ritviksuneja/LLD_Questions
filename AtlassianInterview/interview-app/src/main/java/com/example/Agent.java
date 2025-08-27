package com.example;

import java.util.ArrayList;
import java.util.List;

public class Agent {
    //avg rating = 0.0
    //total ratings = 0
    //(avgRating * totalRatings + inputrating) / (totalRatings + 1)

    private final String id;
    private final String name;
    private final List<Integer> ratings;
    private double averageRating;

    public Agent(String id, String name) {
        this.id = id;
        this.name = name;
        this.ratings = new ArrayList<>();
        averageRating = 0.0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addRating(int rating){
        ratings.add(rating);
    }

    public void computeAverageRating(){
        if(ratings.isEmpty()){
            averageRating = 0.0;
        }

        averageRating = ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public double getAverageRating(){
        return averageRating;
    }

    public int getratingsCount(){
        return ratings.size();
    }
}
