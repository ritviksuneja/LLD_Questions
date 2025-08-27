package com.example;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private final String version;
    private final List<Developer> authors;
    private final DeploymentStatus status;
    
    public Event(String version, DeploymentStatus status) {
        if(version == null || version.isEmpty()){
            throw new IllegalArgumentException("version can't be empty.");
        }
        if(status == null){
            throw new IllegalArgumentException("Deployment status can't be null.");
        }

        this.version = version;
        this.status = status;
        this.authors = new ArrayList<>();
    }

    public void addAuthor(Developer developer){
        this.authors.add(developer);
    }

    public String getVersion() {
        return version;
    }

    public List<Developer> getAuthors() {
        return authors;
    }

    public DeploymentStatus getStatus() {
        return status;
    }
}
