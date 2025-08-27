package com.example;

import java.util.Collections;
import java.util.Map;

public class RouteMatch {
    private final String result;
    private final Map<String, String> params;

    public RouteMatch(String result, Map<String, String> params){
        this.result = result;
        this.params = Collections.unmodifiableMap(params);
    }

    public String getResult(){
        return this.result;
    }

    public Map<String, String> getParams(){
        return this.params;
    }
}
