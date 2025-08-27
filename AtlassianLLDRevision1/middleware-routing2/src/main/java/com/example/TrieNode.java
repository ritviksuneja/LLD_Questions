package com.example;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private final Map<String, TrieNode> children;
    private String result;

    public TrieNode() {
        children = new HashMap<>();
        result = null;
    }

    public boolean hasChild(String segment){
        return children.containsKey(segment);
    }

    public void addChild(String segment){
        children.put(segment, new TrieNode());
    }

    public TrieNode getChild(String segment){
        return children.get(segment);
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }   
}
