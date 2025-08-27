package com.example;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class RouterImpl implements Router{

    private final Map<String, String> routeMap;

    public RouterImpl(){
        //Tricky: Use a map that supports case-insensitive keys and is thread-safe
        routeMap = new ConcurrentSkipListMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    @Override
    public void addRoute(String path, String result) {
        if(path == null || path.isEmpty()) throw new IllegalArgumentException("path should not be empty!");
        if(result == null || result.isEmpty()) throw new IllegalArgumentException("result should not be empty!");

        //Tricky: Use atomic putIfAbsent to prevent race conditions
        String existingValue = routeMap.putIfAbsent(path, result);
        if (existingValue != null) {
            throw new DuplicateRouteException("Route for path '" + path + "' already exists!");
        }
    }

    @Override
    public String callRoute(String path) {
        if(path == null || path.isEmpty()) throw new IllegalArgumentException("path should not be empty!");
        
        //Tricky: A single get is atomic and sufficient since null values are not allowed.
        String result = routeMap.get(path);
        if (result == null) {
            throw new RouteNotFoundException("Route for path '" + path + "' does not exist!");
        }
        return result;
    }

}
