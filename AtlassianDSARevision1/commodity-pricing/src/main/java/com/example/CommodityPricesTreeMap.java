package com.example;

import java.util.HashMap;
import java.util.TreeMap;

public class CommodityPricesTreeMap {
    private final HashMap<Long, Double> updatedPrice;
    private final TreeMap<Double, Integer> priceFrequnecy;

    public CommodityPricesTreeMap(){
        updatedPrice = new HashMap<>();
        priceFrequnecy = new TreeMap<>();
    }

    // if the timestamp already exist in the hashmap, we will reduce the frequency for the price associated in the treemap. 
    // if the frequency reaches 0, we will remove the price entry from the treemap.
    // we will add the incoming price to the treemap as well. we will also update the hashmap for the same.
    public void upsert(DataPoint datapoint){
        Double price = datapoint.getCommodityPrice();
        Long timestamp = datapoint.getTimestamp();

        Double oldPrice = updatedPrice.get(timestamp);

        if(oldPrice != null){
            priceFrequnecy.put(oldPrice, priceFrequnecy.get(oldPrice) - 1);

            if(priceFrequnecy.get(oldPrice) == 0){
                priceFrequnecy.remove(oldPrice);
            }
        }

        priceFrequnecy.put(price, priceFrequnecy.getOrDefault(price, 0) + 1);
        updatedPrice.put(timestamp, price);
    }

    public Double getMaxPrice(){
        if(priceFrequnecy.isEmpty()){
            return 0.0;
        }

        return priceFrequnecy.lastKey();
    }
}
