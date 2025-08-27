package com.example;

import java.util.Set;

public class Organization {
    private Node root;
    private Node lcaNode;

    public Organization(Node root){
        this.root = root;
        lcaNode = null;
    }

    public Node getCommonGroupForEmployees(Node rootGroup, Set<String> targetEmployees){
        lcaNode = null;
        findLCA(rootGroup, targetEmployees);
        return lcaNode;
    }

    int findLCA(Node root, Set<String> targetEmployees){
        int currMatches = 0;
        for(String child : root.getEmployees()){
            if(targetEmployees.contains(child)){
                currMatches++;
            }
        }
        
        for(Node childNode : root.getChildren()){
            currMatches += findLCA(childNode, targetEmployees);
        }

        if(currMatches == targetEmployees.size()){
            if(lcaNode==null)
                lcaNode = root;
        }

        return currMatches;
    }
}
