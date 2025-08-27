package com.example;

import java.util.ArrayList;
import java.util.List;

public class WildcardRouterImpl implements Router{

    private final TrieNode head = new TrieNode();

    @Override
    public void addRoute(String path, String result) {
        String[] segments = path.split("/");
        TrieNode current = head;

        for(String segment : segments){
            if(segment.isEmpty()){
                continue;
            }

            current = current.getOrCreateChild(segment);
        }

        current.setResult(result);
    }

    @Override
    public String callRoute(String path) {
        String[] segments = path.split("/");
        List<String> pathList = new ArrayList<>();

        for(String segment : segments){
            if(segment.isEmpty()){
                continue;
            }

            pathList.add(segment);
        }

        return dfsFind(head, pathList, 0);
    }

    private String dfsFind(TrieNode node, List<String> segments, int index){
        if(index == segments.size()){
            if(node.getResult() != null){
                return node.getResult();
            }

            throw new RouteNotFoundException("Path not found!");
        }

        String segment = segments.get(index);
        TrieNode exactMatch = node.getChild(segment);
        TrieNode wildcardMatch = node.getChild("*");

        if(exactMatch != null){
            try {
                return dfsFind(exactMatch, segments, index + 1);
            } catch (RouteNotFoundException ignored) {
            }
        }

        if(wildcardMatch != null){
            try {
                return dfsFind(wildcardMatch, segments, index + 1);
            } catch (RouteNotFoundException ignored) {
            }
        }

        throw new RouteNotFoundException("Route not found!");
    }
}
