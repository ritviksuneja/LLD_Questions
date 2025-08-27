package com.example;

// Plan:
//     -name: String
//     -product: Products
//     -monthlyPrice: double
//     -planType: PlanType
//     +getters()

public class Plan {
    private final PlanType planType;
    private final Product product;
    private final Double monthlyPrice;

    public Plan(PlanType planType, Product product, Double monthlyPrice){
        this.planType = planType;
        this.product = product;
        this.monthlyPrice = monthlyPrice;
    }
    
    public PlanType getPlanType() {
        return planType;
    }

    public Product getProduct() {
        return product;
    }

    public Double getMonthlyPrice() {
        return monthlyPrice;
    }
}
