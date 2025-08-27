package com.example;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Agent {
    private final String id;
    private final String name;
    private final Map<YearMonth, List<Integer>> ratings;

    public Agent(String id, String name){
        if(id == null || id.isEmpty()) throw new IllegalArgumentException("id can't be empty");
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("name can't be empty");

        this.id = id;
        this.name = name;
        this.ratings = new ConcurrentHashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getRatings() {
        return ratings.get(YearMonth.now());
    }

    //tricky
    public double getAverageRating() {
    List<Integer> monthRatings = ratings.get(YearMonth.now());
        if (monthRatings == null || monthRatings.isEmpty()) {
            return 0.0;
        }
        return monthRatings.stream()
                        .mapToInt(Integer::intValue)
                        .average()
                        .orElse(0.0);
    }

    public int getTotalRatings(){
        return ratings.get(YearMonth.now()).size();
    }

    public void addRating(int rating){
        if(rating < 0 || rating > 5){
            throw new IllegalRatingException("The rating should be between in the range [0,5]");
        }

        if(!ratings.containsKey(YearMonth.now())){
            ratings.put(YearMonth.now(), new CopyOnWriteArrayList<>());
        }
        ratings.get(YearMonth.now()).add(rating);
    }
}
