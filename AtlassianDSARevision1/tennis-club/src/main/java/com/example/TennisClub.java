package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TennisClub {
    private final PriorityQueue<int[]> courtAvailability; //stores in this format -> {avaiableAt, CourtId}

    public TennisClub(){
        courtAvailability = new PriorityQueue<>((c1, c2) -> {
            int cmp = Integer.compare(c1[0], c2[0]);
            return cmp;
        });
    }

    public List<Court> assignCourts(List<Booking> bookings){
        if(bookings.isEmpty()){
            return Collections.emptyList();
        }

        //sorting is also required. didn't do it here. let's also add it.

        int courtId = 1;
        List<Court> courts = new ArrayList<>();

        for(Booking booking : bookings){
            if(!courtAvailability.isEmpty() && booking.getStartTime() >= courtAvailability.peek()[0]){
                int[] top = courtAvailability.poll();
                courts.get(top[1]).addBooking(booking);
                top[0] = booking.getEndTime();
                courtAvailability.offer(top);
            }
            else{
                Court court = new Court(courtId);
                courtId = courtId + 1;
                court.addBooking(booking);
                courts.add(court);
                courtAvailability.offer(new int[]{booking.getEndTime(), courtId});
            }
        }

        return courts;
    }
}
