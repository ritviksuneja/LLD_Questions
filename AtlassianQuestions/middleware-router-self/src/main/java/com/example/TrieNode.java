package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TrieNode {
    private final Map<String, TrieNode> children = new ConcurrentHashMap<>();
    private String result = null;

    public boolean hasChild(String segment){
        return children.containsKey(segment);
    }

    public void addChild(String segment){
        children.put(segment, new TrieNode());
    }

    public TrieNode getChild(String segment){
        return children.get(segment);
    }

    public void setResult(String res){
        this.result = res;
    }

    public String getResult(){
        return result;
    }
}
