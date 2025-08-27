package com.example;

public interface Router {
    void addRoute(String path, String result);
    String getRoute(String path) throws RouteNotFoundException;
}
