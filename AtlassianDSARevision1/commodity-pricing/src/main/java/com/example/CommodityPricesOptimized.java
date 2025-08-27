package com.example;

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

// the new approach is that whenever we insert a datapoint into the heap, we must update the datapoint variable and mark the previous one stale.
// then when fetching the max price, we must check the top for validity and keep polling until we get a valid top. then return the top value.

public class CommodityPricesOptimized {
    private final Map<Long, DataPoint> updatedPrice;
    private final PriorityQueue<DataPoint> maxHeap;

    public CommodityPricesOptimized(){
        updatedPrice = new HashMap<>();
        maxHeap = new PriorityQueue<>((d1, d2) -> {
            return Double.compare(d2.getCommodityPrice(), d1.getCommodityPrice());
        });
    }

    public void addCommodityPrice(List<DataPoint> datapoints){
        for(DataPoint dataPoint : datapoints){
            if(updatedPrice.containsKey(dataPoint.getTimestamp())){
                updatedPrice.get(dataPoint.getTimestamp()).makeInvalid();
            }
            updatedPrice.put(dataPoint.getTimestamp(), dataPoint);
            maxHeap.offer(dataPoint);
        }

        while (!maxHeap.isEmpty() && !maxHeap.peek().isValid()) {
            maxHeap.poll();
        }
    }

    public Double getMaxCommodityPrice(){
        return maxHeap.peek().getCommodityPrice();
    }
}

/* 
if we don't want to modify the datapoint, we don't want to have an isvalid there, then the following is the approach:

Explanation and Amortized O(1) for getMaxCommodityPrice

addCommodityPrice:
When an update occurs (a new DataPoint with an existing timestamp arrives), we simply update the updatedPrice map and add the new DataPoint to the maxHeap. 
We don't remove the old DataPoint from the heap directly, which would be an expensive O(N) operation in a standard binary heap if we didn't know its location, 
or O(log N) if we tracked indices within the heap using another map.
We do, however, keep currentMaxPrice updated. If the new DataPoint has a higher price, we update currentMaxPrice. 
If the old price was the currentMaxPrice, we mark currentMaxPrice as null to trigger a re-calculation on the next getMaxCommodityPrice call.

getMaxCommodityPrice:
If currentMaxPrice is not null, we return it directly, which is O(1).
If currentMaxPrice is null (meaning an update potentially invalidated the previous max), we perform a cleanup:
We poll() elements from the maxHeap's top as long as their timestamp's price in updatedPrice is different from the price stored in the DataPoint on the heap. 
This identifies and removes "stale" elements that are still in the heap but no longer represent the current price for their timestamp.
Once we find a DataPoint at the top of the heap whose price matches the one in updatedPrice for its timestamp, we know it's a valid maximum. 
We set currentMaxPrice to this value and return it.
*/