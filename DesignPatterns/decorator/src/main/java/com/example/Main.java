package com.example;

public class Main {
    public static void main(String[] args) {
        Coffee basicCoffee = new BasicCoffee(10, "This is just coffee with water");

        basicCoffee = new MilkDecorator(basicCoffee);

        System.out.println("price: " + basicCoffee.getPrice());
        System.out.println("description: " + basicCoffee.getDescription());

        
    }
}