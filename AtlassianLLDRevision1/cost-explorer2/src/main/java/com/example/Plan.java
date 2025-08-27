package com.example;

public class Plan {
    private final String name;
    private final Product product;
    private final PlanType type;
    private final Double monthlyPrice;

    public Plan(Double monthlyPrice, String name, Product product, PlanType type) {
        this.monthlyPrice = monthlyPrice;
        this.name = name;
        this.product = product;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Product getProduct() {
        return product;
    }

    public PlanType getType() {
        return type;
    }

    public Double getMonthlyPrice() {
        return monthlyPrice;
    }
}
