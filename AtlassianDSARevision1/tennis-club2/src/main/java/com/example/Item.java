package com.example;

public class Item {
    private final int availableAt;
    private final Court court;

    public Item(int availableAt, Court court) {
        this.availableAt = availableAt;
        this.court = court;
    }

    public int getAvailableAt() {
        return availableAt;
    }

    public Court getCourt() {
        return court;
    }
}
