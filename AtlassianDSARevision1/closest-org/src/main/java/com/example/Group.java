package com.example;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private final String id;
    private final String name;
    private final List<Group> children;
    private Group parent;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Group> getChildren() {
        return children;
    }

    public Group getParent(){
        return parent;
    }

    private void addParent(Group parent){
        this.parent = parent;
    }

    public void addChild(Group child){
        children.add(child);
        child.addParent(this);
    }
}
