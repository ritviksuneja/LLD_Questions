package orgfinder;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String id;
    private Group parent;
    private List<Group> children = new ArrayList<>();

    public Group(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addChild(Group child) {
        child.parent = this;
        children.add(child);
    }

    public Group getParent() {
        return parent;
    }

    public List<Group> getChildren() {
        return children;
    }
}