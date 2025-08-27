package com.example;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private final String id;
    private final String name;

    private final List<Lap> laps;

    public Driver(String id, String name){
        this.id = id;
        this.name = name;
        this.laps = new ArrayList<>();
    }

    public void addLap(Lap lap){
        laps.add(lap);
    }

    public double getAverageLapTime(){
        if(laps.isEmpty()){
            return 0.0;
        }

        return laps.stream().mapToLong(Lap::getLapTime).average().orElse(0.0);
    }

    public long getLastLapTime(){
        if(laps.isEmpty()){
            throw new RuntimeException("No laps recorded for this driver.");
        }

        return laps.get(laps.size() - 1).getLapTime();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
