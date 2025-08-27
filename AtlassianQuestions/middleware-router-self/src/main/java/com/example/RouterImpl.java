package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RouterImpl implements Router{
    private final Map<String, String> routes;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public RouterImpl(){
        routes = new ConcurrentHashMap<>();
    }

    @Override
    public void addRoute(String path, String result) {
        lock.writeLock().lock();

        try {
            routes.put(path, result);
        } finally {
            lock.writeLock().unlock();
        }        
    }

    @Override
    public String getRoute(String path) throws RouteNotFoundException {
        lock.readLock().lock();
        
        try{
            if(!routes.containsKey(path)){
                throw new RouteNotFoundException("Route for path '" + path + "' not found in the router.");
            }

            return routes.get(path);
        } finally {
            lock.readLock().unlock();
        }
    }
}
