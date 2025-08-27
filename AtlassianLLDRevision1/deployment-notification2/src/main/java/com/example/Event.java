package com.example;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private final String version;
    private final List<String> authors;
    private final EventStatus status;

    public Event(EventStatus status, String version) {
        this.status = status;
        this.version = version;
        this.authors = new ArrayList<>();
    }
    public String getVersion() {
        return version;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void addAuthor(String dev){
        authors.add(dev);
    }
}
