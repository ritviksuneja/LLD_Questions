package com.example;

import java.time.LocalDate;

public class Subscription {
    private final Plan plan;
    private final LocalDate startDate;
    private final LocalDate endDate;
    
    public Subscription(Plan plan, LocalDate startDate, LocalDate endDate){
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Subscription(Plan plan, LocalDate startDate){
        this.plan = plan;
        this.startDate = startDate;
        this.endDate = null;
    }
    
    public Plan getPlan() {
        return plan;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
