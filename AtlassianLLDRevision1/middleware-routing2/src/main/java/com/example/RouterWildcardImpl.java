package com.example;

import java.util.ArrayList;
import java.util.List;

public class RouterWildcardImpl implements Router{
    private final TrieNode root;

    public RouterWildcardImpl(){
        this.root = new TrieNode();
    }

    @Override
    public void addRoute(String path, String result){
        if(path == null || path.isEmpty()){
            throw new IllegalArgumentException("path can't be empty.");
        }
        if(result == null || result.isEmpty()){
            throw new IllegalArgumentException("result can't be empty");
        }

        String[] segments = path.split("/");
        TrieNode current = root;

        for(String segment : segments){
            if(segment.isEmpty()){
                continue;
            }

            if(current.hasChild(segment)){
                current = current.getChild(segment);
            }
            else{
                current.addChild(segment);
                current = current.getChild(segment);
            }
        }

        current.addChild(result);
    }

    @Override
    public String callRoute(String path) {
        if(path == null || path.isEmpty()){
            throw new IllegalArgumentException("path can't be empty.");
        }

        String[] segments = path.split("/");

        List<String> pathList = new ArrayList<>();

        for(String segment : segments){
            if(!segment.isEmpty()){
                pathList.add(segment);
            }
        }

        return dfsFind(root, pathList, 0);
    }

    private String dfsFind(TrieNode current, List<String> pathList, int index){
        if(index == pathList.size()){
            if(current.getResult() != null){
                return current.getResult();
            }

            throw new RouteNotFoundException("Route does not exist.");
        }

        TrieNode exactMatch = null;
        TrieNode wildcardMatch = null;

        if(current.hasChild(pathList.get(index))){
            exactMatch = current.getChild(pathList.get(index));
        }

        if(current.hasChild("*")){
            wildcardMatch = current.getChild("*");
        }

        if(exactMatch != null){
            try {
                return dfsFind(exactMatch, pathList, index + 1);
            } catch (RouteNotFoundException ignorException) {
            }
        }

        if(wildcardMatch != null){
            try {
                return dfsFind(wildcardMatch, pathList, index + 1);
            } catch (RouteNotFoundException ignorException) {
            }
        }

        throw new RouteNotFoundException("Route does not exist.");
    }
}
