package com.example;

import java.util.Map;
import java.util.TreeMap;

public class RouterImpl implements Router {

    private final Map<String, String> routeMap;

    public RouterImpl(){
        this.routeMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    @Override
    public void addRoute(String path, String result) {
        if(path == null || path.isEmpty()){
            throw new IllegalArgumentException("path can't be empty.");
        }
        if(result == null || result.isEmpty()){
            throw new IllegalArgumentException("result can't be empty");
        }
        if(routeMap.containsKey(path)){
            throw new DuplicateRouteException("Route for path '" + path + "' already exists.");
        }

        routeMap.put(path, result);
    }

    @Override
    public String callRoute(String path) {
        if(path == null || path.isEmpty()){
            throw new IllegalArgumentException("path can't be empty.");
        }

        if(!routeMap.containsKey(path)){
            throw new RouteNotFoundException("Route for path '" + path + "' does not exist.");
        }

        return routeMap.get(path);
    }

}
