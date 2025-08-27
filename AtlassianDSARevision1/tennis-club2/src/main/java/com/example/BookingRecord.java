package com.example;

public class BookingRecord {
    private final int id;
    private final int startTime;
    private final int finishTime;

    public BookingRecord(int id, int startTime, int finishTime) {
        if(finishTime <= startTime){
            throw new IllegalArgumentException("startTime must be lesser than finishTime");
        }
        this.id = id;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public int getId() {
        return id;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }
}
