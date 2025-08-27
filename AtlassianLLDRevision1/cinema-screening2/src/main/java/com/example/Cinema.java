package com.example;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private final List<Screening> screenings;
    private final int openingTime;
    private final int closingTime;

    public Cinema(int openingTime, int closingTime) {
        if(openingTime < 0 || closingTime < 0){
            throw new IllegalArgumentException("openingTime and closingTime should be greater than 0.");
        }

        this.openingTime = openingTime;
        this.closingTime = closingTime;
        screenings = new ArrayList<>();
    }

    public boolean addScreening(Screening currScreening){
        int currStartTime = currScreening.getStartTime();
        int currEndTime = currScreening.getEndTIme();

        if(currStartTime < openingTime || currStartTime > closingTime || currEndTime < openingTime || currEndTime > closingTime){
            return false;
        }

        for(Screening screening :  screenings){
            int startTime = screening.getStartTime();
            int endTime = screening.getEndTIme();

            if(currStartTime < endTime && currEndTime > startTime){
                return false;
            }
        }

        screenings.add(currScreening);
        return true;
    }
}