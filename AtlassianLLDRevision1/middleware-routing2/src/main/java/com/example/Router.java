package com.example;

public interface Router {
    public void addRoute(String path, String result);
    public String callRoute(String path);
}
