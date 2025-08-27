package com.example;

import java.util.ArrayList;
import java.util.List;

public class Court {
    private final int id;
    private final List<BookingRecord> bookings;

    public Court(int id) {
        this.id = id;
        this.bookings = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<BookingRecord> getBookings() {
        return bookings;
    }

    public void addBooking(BookingRecord booking){
        bookings.add(booking);
    }
}
