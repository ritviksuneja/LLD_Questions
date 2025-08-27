package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//11 PM
public class ShortestDistance {
    private final Map<String, Map<String, Integer>> graph;

    public ShortestDistance(){
        graph = new HashMap<>();
    }

    public void addEdge(String src, String dest, int weight){
        graph.putIfAbsent(src, new HashMap<>());
        graph.get(src).put(dest, weight);
    }

    public int findShortestPath(String src, String dest){
        PriorityQueue<Item> minHeap = new PriorityQueue<>((a1, a2)->{
            return Integer.compare(a1.getWeight(), a2.getWeight());
        });

        minHeap.add(new Item(src, 0));
        
        Set<String> allNodes = graph.keySet();
        Map<String, Integer> distMap = new HashMap<>();

        for(String node : allNodes){
            distMap.put(node, Integer.MAX_VALUE);
        }

        distMap.put(src, 0);

        while(!minHeap.isEmpty()){
            Item top = minHeap.poll();
            String node = top.getNode();
            int weight = top.getWeight();

            if(graph.get(node) == null){
                continue;
            }

            Set<String> neighbours = graph.get(node).keySet();

            for(String neighbour : neighbours){
                if(distMap.get(neighbour) > (graph.get(node).get(neighbour) + weight)){
                    distMap.put(neighbour, graph.get(node).get(neighbour) + weight);
                    minHeap.add(new Item(neighbour, graph.get(node).get(neighbour) + weight));
                }
            }
        }

        if(distMap.get(dest) != Integer.MAX_VALUE){
            return distMap.get(dest);
        }

        return Integer.MAX_VALUE;
    }
}
