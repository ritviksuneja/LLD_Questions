package com.example;

public class BasicCoffee implements Coffee{
    private final int price;
    private final String description;

    public BasicCoffee(int price, String description){
        this.price = price;
        this.description = description;
    }


    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
