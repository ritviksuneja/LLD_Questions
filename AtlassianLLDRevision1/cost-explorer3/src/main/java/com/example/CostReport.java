package com.example;

import java.time.YearMonth;
import java.util.Map;

public class CostReport {
    private final Map<YearMonth, Double> monthlyCost;
    private final Double yearlyCost;

    public Map<YearMonth, Double> getMonthlyCost() {
        return monthlyCost;
    }

    public Double getYearlyCost() {
        return yearlyCost;
    }

    public CostReport(Map<YearMonth, Double> monthlyCost, Double yearlyCost) {
        this.monthlyCost = monthlyCost;
        this.yearlyCost = yearlyCost;
    }
}
