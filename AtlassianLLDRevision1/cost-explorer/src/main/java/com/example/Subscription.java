package com.example;

import java.time.LocalDate;

public class Subscription {
    private final LocalDate startDate;
    private LocalDate endDate;
    private final Plan plan;

    public Subscription(LocalDate startDate, LocalDate endDate, Plan plan){
        this.startDate = startDate;
        this.endDate = endDate;
        this.plan = plan;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
    }
}
