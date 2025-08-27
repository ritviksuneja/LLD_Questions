package com.example;

public class Notification {
    private final String author;
    private final String version;

    public Notification(String author, String version) {
        this.author = author;
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }
}   
