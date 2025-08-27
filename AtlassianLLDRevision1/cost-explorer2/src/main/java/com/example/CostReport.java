package com.example;

import java.time.YearMonth;
import java.util.Map;

public class CostReport {
    private final Map<YearMonth, Double> monthlyPrice;
    private final Double yearlyPrice;

    public CostReport(Map<YearMonth, Double> monthlyPrice, Double yearlyPrice) {
        this.monthlyPrice = monthlyPrice;
        this.yearlyPrice = yearlyPrice;
    }

    public Map<YearMonth, Double> getMonthlyPrice() {
        return monthlyPrice;

    }
    
    public Double getYearlyPrice() {
        return yearlyPrice;
    }
}
