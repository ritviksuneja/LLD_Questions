package com.example;

// Popularity

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//     -contents: Map<Integer, Content>
//     -contentPopularity: TreeSet<Content>
//     +addContent(Content content): void
//     +alterPopularity(int contentId, ActionType actionType): void
//     +getMostPopularContent(): int

public class Popularity {
    private final Map<Integer, Content> contentMap;
    private final PriorityQueue<Content> contentPopularity;

    public Popularity(){
        this.contentMap = new HashMap<>();
        this.contentPopularity = new PriorityQueue<>((c1, c2) -> {
            return Integer.compare(c2.getPopularity(), c1.getPopularity());
        });
    }

    public void addContent(Content content){
        if(contentMap.containsKey(content.getId())){
            throw new RuntimeException("The content with id '" + content.getId() + "' already exists.");
        }

        contentMap.put(content.getId(), content);
    }

    public void alterPopularity(int contentId, ActionType action){
        if(!contentMap.containsKey(contentId)){
            throw new RuntimeException("The content with id '" + contentId + "' does not exist.");
        }

        switch (action) {
            case INCREASE_POPULARITY -> contentMap.get(contentId).increasePopularity();
            case DECREASE_POPULARITY -> contentMap.get(contentId).decreasePopularity();
            default -> throw new AssertionError("Illegal action provided.");
        }

        contentPopularity.add(contentMap.get(contentId));
    }

    public int getMostPopularContent(){
        while(!contentPopularity.isEmpty()){
            Content top = contentPopularity.poll();
            if(contentMap.get(top.getId()).getPopularity() == top.getPopularity() && top.getPopularity() > 0){
                return top.getId();
            }
        }
        return -1;
    }
}
