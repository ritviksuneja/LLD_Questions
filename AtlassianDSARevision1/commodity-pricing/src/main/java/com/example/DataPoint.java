package com.example;

public class DataPoint {
    private final long timestamp;
    private final Double commodityPrice;
    private boolean valid;

    public DataPoint(long timestamp, Double commodityPrice) {
        this.timestamp = timestamp;
        this.commodityPrice = commodityPrice;
        this.valid = true;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public boolean isValid(){
        return valid;
    }

    public void makeInvalid(){
        valid = false;
    }
}
