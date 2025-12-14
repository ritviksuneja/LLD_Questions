package com.example;

public class MilkDecorator implements Coffee{
    private final Coffee coffee;

    public MilkDecorator(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public int getPrice() {
        return 10 + coffee.getPrice();
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + "with milk.";
    }
}
