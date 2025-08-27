package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Organization:
//     -root: Group
//     -employeeToGroupMap: Map<String, String> //employeeId, groupId
//     -groups: Map<String, Group> //groupId
//     +addGroup(Group group): void
//     +addEmployeeToGroup(String employeeId, String groupId): void
//     +findLCA(Set<Employee> employeeSet): Group

public class Organization {
    private final Map<String, String> employeeToGroupMapping; //employeeName, groupId
    private final Map<String, Group> groups; //groupId

    public Organization(){
        employeeToGroupMapping = new HashMap<>();
        groups = new HashMap<>();
    }

    public void addGroup(Group group){
        String groupId = group.getId();
        groups.put(groupId, group);
    }

    public void addEmployeeToGroup(String employeeName, String groupId){
        employeeToGroupMapping.put(employeeName, groupId);
    }

    //If this will be called many times on the same tree, consider preprocessing with Binary Lifting / Euler Tour to bring each LCA query down to O(log h) or O(1).
    public Group findLCA(Set<String> employeeSet){
        List<Group> inputGroups = new ArrayList<>();

        for(String employee : employeeSet){
            inputGroups.add(groups.get(employeeToGroupMapping.get(employee)));
        }

        List<Group> potentialLCAs = findPathFromRoot(inputGroups.get(0));

        for(Group g : inputGroups){
            List<Group> path = findPathFromRoot(g);
            int i = 0;
            
            while(i < path.size() && i < potentialLCAs.size() && potentialLCAs.get(i) == path.get(i)){
                i++;
            }

            potentialLCAs = potentialLCAs.subList(0, i);
        }

        if(potentialLCAs.isEmpty()){
            return null;
        }

        return potentialLCAs.get(potentialLCAs.size() - 1);
    }

    public List<Group> findPathFromRoot(Group leaf){
        LinkedList<Group> path = new LinkedList<>();
        path.add(leaf);

        Group parent = leaf.getParent();

        while(parent != null){
            path.addFirst(parent);
            parent = parent.getParent();
        }
        
        return path;
    }
}
