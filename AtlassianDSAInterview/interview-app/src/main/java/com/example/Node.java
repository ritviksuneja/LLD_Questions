package com.example;

import java.util.List;

public class Node {
    private final String id;
    private final List<Node> children;
    private final List<String> employees;

    
    public Node(String id, List<Node> children, List<String> employees){
        this.id = id;
        this.children = children;
        this.employees = employees;
    }

    public String getId() {
        return id;
    }

    public List<Node> getChildren() {
        return children;
    }

    public List<String> getEmployees() {
        return employees;
    }
}
