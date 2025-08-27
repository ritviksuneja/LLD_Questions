package com.example;

// Group:

import java.util.ArrayList;
import java.util.List;

//     -name: String
//     -id:String
//     -children: List<Group>
//     -parent: Group
//     +constructor()
//     -addParent(Group group): void
//     +addChild(Group g): void

public class Group {
    private final String id;

    private final List<Group> children;
    private Group parent;

    public Group(String id){
        this.id = id;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public void addChild(Group child){
        children.add(child);
        child.parent = this;
    }

    public Group getParent(){
        return parent;
    }

    public String getId() {
        return id;
    }
}
