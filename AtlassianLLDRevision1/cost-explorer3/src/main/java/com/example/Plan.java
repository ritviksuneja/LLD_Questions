package com.example;

public class Plan {
    private final Product product;
    private final double monthlyPrice;
    private final PlanType type;
    
    public Plan(Product product, double monthlyPrice, PlanType type) {
        this.product = product;
        this.monthlyPrice = monthlyPrice;
        this.type = type;
    }
    
    public Product getProduct() {
        return product;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public PlanType getType() {
        return type;
    }
}
