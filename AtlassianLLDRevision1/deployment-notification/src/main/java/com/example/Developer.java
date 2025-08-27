package com.example;

public class Developer {
    private final String id;
    private final String name;

    public Developer(String id, String name) {
        if(id == null || id.isEmpty()){
            throw new IllegalArgumentException("id can't be empty");
        }
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("name can't be empty");
        }

        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
