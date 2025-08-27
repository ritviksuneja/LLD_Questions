package com.example;

// Organization:

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//     -employeeToGroupMap: Map<String, String> //employeeName, groupId
//     -groups: Map<String, Group>
//     +findLCA(List<String> employees):Group
//     +addGroup(Group g): void
//     +addEmployeeToGroup(String employeeName, String groupId): void

public class Organization {
    private final Map<String, String> employeeToGroupMap;
    private final Map<String, Group> groups;
    
    public Organization(){
        this.employeeToGroupMap = new HashMap<>();
        this.groups = new HashMap<>();
    }

    public void addGroup(Group g){
        if(groups.containsKey(g.getId())){
            throw new RuntimeException("Duplicate group. Group with this id already exists.");
        }

        groups.put(g.getId(), g);
    }

    public void addEmployeeToGroup(String empName, String groupId){
        if(employeeToGroupMap.containsKey(empName)){
            throw new RuntimeException("This employee already belongs to a group.");
        }
        if(!groups.containsKey(groupId)){
            throw new RuntimeException("Input group does not exist.");
        }

        employeeToGroupMap.put(empName, groupId);
    }

    //I will first get all the groups these employee belong to. Then I will select first group out of them, find path from root. the groups in that become out candidate.
    //now I will just find the common groups between all the paths for all such groups.
    public Group findLCA(List<String> inputEmployees){
        if(inputEmployees.isEmpty()){
            return null;
        }

        List<Group> inputGroups = new ArrayList<>();

        for(String empName : inputEmployees){
            if(!employeeToGroupMap.containsKey(empName)){
                continue;
            }
            inputGroups.add(groups.get(employeeToGroupMap.get(empName)));
        }

        if(inputGroups.isEmpty()){
            return null;
        }

        List<Group> potentialLCAs = findPathFromRoot(inputGroups.get(0));
        
        if(potentialLCAs.isEmpty()){
            return null;
        }

        for(Group g : inputGroups.subList(1, inputGroups.size())){
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

    private List<Group> findPathFromRoot(Group leaf){
        LinkedList<Group> path = new LinkedList<>();
        Group current = leaf;

        while(current != null){
            path.addFirst(current);
            current = current.getParent();
        }

        return path;
    }
}
