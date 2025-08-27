package com.example;

public class Lap {
    private final long timeMillis;

    public Lap(long timeMillis) {
        if(timeMillis <= 0){
            throw new IllegalArgumentException("Lap time can't be lesser than or equal to 0.");
        }
        this.timeMillis = timeMillis;
    }

    public long getTimeMillis() {
        return timeMillis;
    }
}
