package com.example;

import java.util.HashMap;
import java.util.Map;

public class PathParamRouterImpl{
    private final TrieNodeWithParam root = new TrieNodeWithParam();

    public void addRoute(String path, String result) {
        String[] segments = path.split("/");
        TrieNodeWithParam current = root;

        for(String segment : segments){
            if(!segment.isEmpty()){
                String key = segment;

                if(segment.startsWith(":")){
                    key = ":";
                }

                if(!current.hasChild(key)){
                    current.addChild(key);
                }

                current = current.getChild(key);
                
                if(segment.startsWith(":")){
                    current.setParam(segment.substring(1));
                }
            }
        }

        current.setResult(result);
    }

    public RouteMatch getRoute(String path) throws RouteNotFoundException {
        String[] segments = path.split("/");
        final Map<String, String> params = new HashMap<>();
        TrieNodeWithParam current = root;

        for(String segment : segments){
            if(!segment.isEmpty()){
                TrieNodeWithParam exact = current.getChild(segment);

                if(exact != null){
                    current = exact;
                    continue;
                }

                TrieNodeWithParam paramNode = current.getChild(":");

                if(paramNode != null){
                    params.put(paramNode.getParam(), segment);
                    current = paramNode;
                    continue;
                }

                TrieNodeWithParam wildcardNode = current.getChild("*");

                if(wildcardNode != null){
                    current = wildcardNode;
                    continue;
                }

                throw new RouteNotFoundException("No match found for: " + path);
            }

            if(current.getResult() != null){
                return new RouteMatch(current.getResult(), params);
            }
        }

        throw new RouteNotFoundException("No match found for: " + path);
    }
}
