package com.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Group {
    private final String id;
    private final Set<Group> parents;
    private final List<Group> children;

    public Group(String id) {
        this.id = id;
        this.children = new CopyOnWriteArrayList<>();
        this.parents = new HashSet<>();
    }

    public void addChild(Group child){
        children.add(child);
        child.addParent(this);
    }

    public String getId() {
        return id;
    }

    public List<Group> getParents() {
        return parents.stream().collect(Collectors.toList());
    }

    public List<Group> getChildren() {
        return children;
    }

    private void addParent(Group parent){
        this.parents.add(parent);
    }
}
