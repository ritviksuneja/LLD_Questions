package com.example;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class CostReport {
    private final Map<YearMonth, Double> monthlyCost;
    private Double yearlyCost;

    public CostReport(){
        this.monthlyCost = new HashMap<>();
    }

    public void addMonthlyCost(YearMonth month, Double cost){
        monthlyCost.put(month, cost);
    }

    public void addYearlyCost(Double cost){
        yearlyCost = cost;
    }
}
