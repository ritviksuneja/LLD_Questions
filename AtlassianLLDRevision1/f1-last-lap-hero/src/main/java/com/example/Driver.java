package com.example;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private final String name;
    private final String id;
    private final List<Lap> laps;

    public Driver(String name, String id) {
        this.name = name;
        this.id = id;
        this.laps = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Lap> getLaps() {
        return laps;
    }

    public void addLap(Lap lap){
        laps.add(lap);
    }

    //understand the difference between this and laps.stream().mapToLong(Lap::getTimeMillis).average().orElse(0.0);
    public double getAverageLapTime(){
        return laps.stream().mapToLong(lap -> (lap.getTimeMillis())).average().orElse(0.0);
    }
}
