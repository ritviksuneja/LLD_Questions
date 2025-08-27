package com.example;

public class Booking {
    private final int id;
    private final int startTime;
    private final int endTime;

    public Booking(int id, int startTime, int endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public int getStartTime() {
        return startTime;
    }
    
    public int getEndTime() {
        return endTime;
    }
}
