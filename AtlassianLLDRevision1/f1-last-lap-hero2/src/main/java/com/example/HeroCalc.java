package com.example;

import java.util.HashMap;
import java.util.Map;

public class HeroCalc {
    private final Map<String, Driver> driversMap;
    private Driver lastHero;
    private Double lastHeroImprovement;

    public HeroCalc(){
        driversMap = new HashMap<>();
        lastHero = null;
        lastHeroImprovement = Double.NEGATIVE_INFINITY;
    }

    public void addDriver(Driver driver){
        if(driversMap.containsKey(driver.getId())){
            throw new DuplicateDriverException("The driver already exists in the system.");
        }

        driversMap.put(driver.getId(), driver);
    }

    public Driver addLapTime(String driverId, long startTime, long endTime){
        if(driverId == null || driverId.isEmpty()){
            throw new IllegalArgumentException("Driver id can't be empty");
        }
        
        if(!driversMap.containsKey(driverId)){
            throw new DriverNotFoundException("Driver not found");
        }

        Driver driver = driversMap.get(driverId);
        double avgLapTIme = driver.getAverageLapTime();

        driver.addLap(new Lap(startTime, endTime));

        double improvement = Double.NEGATIVE_INFINITY;

        if(avgLapTIme != 0.0){
            improvement = (avgLapTIme - (double)driver.getLastLapTime())/avgLapTIme;
        }
        
        if(improvement > lastHeroImprovement){
            lastHeroImprovement = improvement;
            lastHero = driver;
        }

        return lastHero;
    }
}
