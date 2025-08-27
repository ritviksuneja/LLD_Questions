package com.example;

public class DeploymentNotification {
    private final String version;
    private final Developer author;

    public DeploymentNotification(Developer author, String version) {
        this.author = author;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public Developer getAuthor() {
        return author;
    }
}
