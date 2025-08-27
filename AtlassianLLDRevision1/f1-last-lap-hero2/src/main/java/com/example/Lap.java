package com.example;

public class Lap {
    private final long startTime;
    private final long endTime;

    public Lap(long startTime, long endTime) {
        if(startTime < 0 || endTime < 0){
            throw new IllegalArgumentException("Please provide non-negative input for startTime and endTime");
        }
        if(endTime <= startTime){
            throw new IllegalArgumentException("endTime should be greater than startTime");
        }

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public long getLapTime(){
        return endTime - startTime;
    }
}