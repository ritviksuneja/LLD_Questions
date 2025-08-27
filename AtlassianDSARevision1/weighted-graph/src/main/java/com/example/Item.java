package com.example;

public class Item {
    private final String node;
    private final int weight;

    public Item(String node, int weight) {
        this.node = node;
        this.weight = weight;
    }

    public String getNode() {
        return node;
    }

    public int getWeight() {
        return weight;
    }
}
