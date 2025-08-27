package com.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Employee {
    private final String id;
    private final String name;
    private final Set<Group> groups;
    
    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
        this.groups = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setGroups(Group group){
        groups.add(group);
    }

    public List<Group> getGroupsList(){
        return groups.stream().collect(Collectors.toList());
    }
}
