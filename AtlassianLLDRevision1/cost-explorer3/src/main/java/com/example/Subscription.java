package com.example;

import java.time.LocalDate;

public class Subscription {
    private final Plan plan;
    private final LocalDate startTime;
    private final LocalDate endTime;

    public Subscription(Plan plan, LocalDate startTime, LocalDate endTime) {
        this.plan = plan;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Subscription(Plan plan, LocalDate startTime) {
        this.plan = plan;
        this.startTime = startTime;
        this.endTime = null;
    }

    public Plan getPlan() {
        return plan;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

}
