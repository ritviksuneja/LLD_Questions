package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WildCardRouterImpl implements Router{
    private final TrieNode root = new TrieNode();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void addRoute(String path, String result) {
        lock.writeLock().lock();

        try {
            String[] segments = path.split("/");
            TrieNode current = root;

            for(String segment : segments){
                if(!segment.isEmpty()){
                    if(!current.hasChild(segment)){
                        current.addChild(segment);
                    }
                    current = current.getChild(segment);
                }
            }

            current.setResult(result);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public String getRoute(String path) throws RouteNotFoundException {
        String[] segments = path.split("/");
        List<String> pathList = new ArrayList<>();

        for(String segment : segments){
            if(!segment.isEmpty()){
                pathList.add(segment);
            }
        }

        return dfsMatch(root, pathList, 0);
    }

    private String dfsMatch(TrieNode node, List<String> segments, int index){
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
                return dfsMatch(exactMatch, segments, index + 1);
            } catch (RouteNotFoundException ignored) {
            }
        }

        if(wildcardMatch != null){
            try {
                return dfsMatch(wildcardMatch, segments, index + 1);
            } catch (RouteNotFoundException ignored) {
            }
        }
        
        throw new RouteNotFoundException("Route not found!");
    }

}
