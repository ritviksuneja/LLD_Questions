package com.example;

import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Agent {
    private final String id;
    private final String name;
    private final Map<YearMonth, List<Integer>> monthlyRatings;

    public Agent(String id, String name){
        this.id = id;
        this.name = name;
        this.monthlyRatings = new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addRating(int rating){
        if(rating < 1 || rating > 5){
            throw new InvalidRatingException("Invalid rating provided. The rating must be in the range [1,5].");
        }
        
        monthlyRatings.computeIfAbsent(YearMonth.now(), k -> new CopyOnWriteArrayList<>()).add(rating);
    }
    
    public Double getAverageRating(YearMonth yMonth){
        List<Integer> ratings = monthlyRatings.getOrDefault(yMonth, Collections.emptyList());
        return ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public int getTotalRatings(YearMonth yMonth){
        return monthlyRatings.getOrDefault(yMonth, Collections.emptyList()).stream()
                .mapToInt(Integer::intValue).sum();
    }

    public Map<YearMonth, List<Integer>> getMonthlyRatings() {
        return monthlyRatings;
    }
}
