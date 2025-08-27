package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

//     -courtAvailability: PriorityQueue<Item>
//     +assignCourts(List<BookingRecord> inputBookings): List<Court>

public class TennisCourt {
    private final PriorityQueue<Item> courtAvailability;

    public TennisCourt(){
        courtAvailability = new PriorityQueue<>((i1, i2) -> {
            return Integer.compare(i1.getAvailableAt(), i2.getAvailableAt());
        });
    }

    public List<Court> assignCourts(List<BookingRecord> bookings){
        if(bookings.isEmpty()){
            return Collections.emptyList();
        }

        int id = 1;

        List<Court> result = new ArrayList<>();

        Court firstCourt = new Court(id);
        courtAvailability.add(new Item(0, firstCourt));
        result.add(firstCourt);

        bookings.sort((b1, b2) -> {
            return Integer.compare(b1.getStartTime(), b2.getStartTime());
        });

        for(BookingRecord booking : bookings){
            Item item = courtAvailability.peek();
            Court court = item.getCourt();
            int availableAt = item.getAvailableAt();

            if(availableAt <= booking.getStartTime()){
                court.addBooking(booking);
                availableAt = booking.getFinishTime();
                courtAvailability.poll();
                courtAvailability.add(new Item(availableAt, court));
            }
            else{
                Court newCourt = new Court(++id);
                newCourt.addBooking(booking);
                courtAvailability.add(new Item(booking.getFinishTime(), newCourt));
                result.add(newCourt);
            }
        }

        return result;
    }
}
