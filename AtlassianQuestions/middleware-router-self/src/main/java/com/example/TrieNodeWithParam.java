package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TrieNodeWithParam {
    Map<String, TrieNodeWithParam> children = new ConcurrentHashMap<>();
    String result = null;
    String param = null;

    public boolean hasChild(String segment){
        return children.containsKey(segment);
    }

    public void addChild(String segment){
        children.put(segment, new TrieNodeWithParam());
    }
    
    public TrieNodeWithParam getChild(String segment){
        return children.get(segment);
    }

    public void setResult(String res){
        result = res;
    }

    public String getResult(){
        return result;
    }

    public void setParam(String param){
        this.param = param;
    }

    public String getParam(){
        return param;
    }
}
