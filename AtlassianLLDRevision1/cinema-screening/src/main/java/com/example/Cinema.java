package com.example;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private final List<Screening> schedule;
    private final int openingTime;
    private final int closingTime;

    public Cinema(int openingTime, int closingTime) {
        this.schedule = new ArrayList<>();
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public List<Screening> getSchedule() {
        return schedule;
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public boolean addScreening(Screening screening){
        if(screening == null){
            throw new IllegalArgumentException("Screening can't be null.");
        }

        if(screening.getStartTime() < openingTime || screening.getEndTime() > closingTime){
            throw new IllegalArgumentException("Screening not allowed as it is out of cinema timings.");
        }

        int screeningStartTime = screening.getStartTime();
        int screeningEndTime = screening.getEndTime();

        for(Screening screen : schedule){
            int screenStartTime = screen.getStartTime();
            int screenEndTime = screen.getEndTime();

            if(screeningStartTime < screenEndTime && screeningEndTime > screenStartTime){
                return false;
            }
        }

        schedule.add(screening);
        return true;
    }
}
