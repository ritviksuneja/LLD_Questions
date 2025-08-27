package com.example;

import java.util.ArrayList;
import java.util.List;

public class Court {
    private final List<Booking> bookings;
    private final int id;

    public Court(int id) {
        bookings = new ArrayList<>();
        this.id = id;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking){
        bookings.add(booking);
    }

    public int getId() {
        return id;
    }
}
