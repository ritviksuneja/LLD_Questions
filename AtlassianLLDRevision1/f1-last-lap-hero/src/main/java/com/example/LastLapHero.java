package com.example;

import java.util.HashMap;
import java.util.Map;

public class LastLapHero {
    private final Map<String, Driver> drivers;
    private String lastLapHeroId;
    private Double lastHeroTime;

    public LastLapHero(){
        drivers = new HashMap<>();
        lastLapHeroId = null;
        lastHeroTime = 0.0;
    }

    public void addDriver(Driver driver){
        if(drivers.containsKey(driver.getId())){
            throw new DuplicateDriverException("Driver with id '" + driver.getId() + "' already exists!");
        }

        drivers.put(driver.getId(), driver);
    }

    // This logic is flawed. We are comparing average times. 
    // But we should compare last lap time % change when compared with avg.
    // So, change this.
    public void addLap(String driverId, Lap lap){
        if(!drivers.containsKey(driverId)){
            throw new DriverNotFoundException("Driver with id '" + driverId + "' does not exist!");
        }
        
        Driver driver = drivers.get(driverId);

        driver.addLap(lap);

        Double avgTime = driver.getAverageLapTime();

        if(lastLapHeroId == null || lastLapHeroId.equals(driverId)){
            lastLapHeroId = driverId;
            lastHeroTime = avgTime;
            return;
        }

        if(avgTime > lastHeroTime){
            lastHeroTime = avgTime;
            lastLapHeroId = driverId;
        }
    }

    public String getLastLapHero(){
        return lastLapHeroId;
    }
}
