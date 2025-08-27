package com.example;

// CommodityPrices

import static java.lang.Double.doubleToRawLongBits;
import static java.lang.Double.longBitsToDouble;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

//     -updatedPrice: Map<int, Double> //<timeStamp, commodityPrice>
//     -maxHeap: PriorityQueue<{Double, int}> //{commodityPrice, timeStamp}
//     +addCommodityPrice(List<DataPoint>) : void
//     +getmaxCommodityPrice(): double;

// //if we maintain a max heap, which will be having {commodityPrice, timeStamp} as data stored in it. we can easily get the max commodityPrice.
// but the catch is that when there's conflict on timeStamp, we must resolve it. Now, we can keep a Map<timeStamp, commodityPrice> for keeping updated
// price of any given timeStamp. 
// Whenever we are trying to fetch the max commodity price at any given time, we can absolutely check it once with the value in the map. if it matches, good.
// if it doesn't match, the max heap's top is stale, we must remove it. and keep on checking until the max heap is empty. as soon as we find it, we return it.
// simple.

public class CommodityPrices {
    private final Map<Long, Double> updatedPrice;
    private final PriorityQueue<long[]> maxHeap;

    public CommodityPrices(){
        updatedPrice = new HashMap<>();
        maxHeap = new PriorityQueue<>((a1, a2) -> {
            return Double.compare(longBitsToDouble(a2[0]), longBitsToDouble(a1[0]));
        });
    }

    public void addCommodityPrice(List<DataPoint> dataPoints){
        for(DataPoint dataPoint : dataPoints){
            updatedPrice.put(dataPoint.getTimestamp(), dataPoint.getCommodityPrice());
            maxHeap.offer(new long[]{doubleToRawLongBits(dataPoint.getCommodityPrice()), dataPoint.getTimestamp()});
        }
    }

    public Double getMaxCommodityPrice(){
        if(maxHeap.isEmpty()){
            return 0.0;
        }

        while(!maxHeap.isEmpty()){
            Double maxPrice = longBitsToDouble(maxHeap.peek()[0]);
            Long timeStamp = maxHeap.peek()[1];

            if(updatedPrice.containsKey(timeStamp)){
                if(updatedPrice.get(timeStamp).equals(maxPrice)){
                    return maxPrice;
                }
                else{
                    maxHeap.poll();
                }
            }
            else{
                return maxPrice;
            }
        }

        return 0.0;
    }
}
