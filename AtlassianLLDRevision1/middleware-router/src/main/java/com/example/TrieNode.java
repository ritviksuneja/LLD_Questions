package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class TrieNode {
    private final Map<String, TrieNode> children = new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER);
    private String result = null;

    //Tricky
    // public void addChild(String segment){
    //     children.put(segment, new TrieNode());
    // }
    // This is inefficient because it requires two lookups in the map for every segment: 
    // one for addChild (internally putIfAbsent) and one for getChild. It's also less readable.

    public TrieNode getOrCreateChild(String segment) {
        // This is the standard, atomic "get-or-create" operation.
        return children.computeIfAbsent(segment, k -> new TrieNode());
    }

    public TrieNode getChild(String segment){
        return children.get(segment);
    }

    public boolean hasChild(String segment){
        return children.get(segment) != null;
    }

    public void setResult(String result){
        this.result = result;
    }

    public String getResult(){
        return this.result;
    }
}
