package com.example;

import java.util.ArrayList;
import java.util.List;

public class Agent {
    private final String id;
    private final String name;
    private final List<Integer> ratings;

    public Agent(String id, String name) {
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("id can't be null");
        }
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("name can't be null");
        }

        this.id = id;
        this.name = name;
        this.ratings = new ArrayList<>();
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

    public double getAverageRating() {
        if(ratings.isEmpty()){
            return 0.0;
        }

        return ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
}
