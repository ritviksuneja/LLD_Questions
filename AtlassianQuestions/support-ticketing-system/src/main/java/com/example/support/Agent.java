package com.example.support;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Agent {
    private final String id;
    private final String name;
    private final List<Integer> ratings;

    public Agent(String id, String name) {
        this.id = id;
        this.name = name;
        this.ratings = new CopyOnWriteArrayList<>();
    }

    public void addRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new InvalidRatingException("Rating must be between 1 and 5");
        }
        ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int r : ratings) {
            sum += r;
        }
        return sum / (double) ratings.size();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getTotalRatingsCount(){
        return ratings.stream().reduce(0, Integer::sum);
    }
}