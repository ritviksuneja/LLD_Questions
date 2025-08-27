package com.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RatingSystem {
    private final Map<String, Agent> agents;

    public RatingSystem(){
        agents = new HashMap<>();
    }

    public void addAgent(Agent agent){
        if(agents.containsKey(agent.getId())){
            throw new RuntimeException("Agent with id '" + agent.getId() + "' already exists.");
        }

        agents.put(agent.getId(), agent);
    }

    public void addRating(String agentId, int rating){
        if(rating < 1 || rating > 5){
            throw new IllegalArgumentException("Rating provided is out of the acceptable range [1..5].");
        }
        if(!agents.containsKey(agentId)){
            throw new RuntimeException("Agent with id '" + agentId + "' does not exist.");
        }

        Agent agent = agents.get(agentId);
        agent.addRating(rating);
        agent.computeAverageRating();
    }

    public List<Agent> getAgentsByAverageRating(){
        //List<Agent> agentsList = agents.values().stream().collect(Collectors.toList());
        if(agents.isEmpty()){
            return Collections.emptyList();
        }

        return agents.values().stream().sorted((a1, a2) -> {
            int cmp = Double.compare(a2.getAverageRating(), a1.getAverageRating());
            if(cmp == 0){
                return Integer.compare(a2.getratingsCount(), a1.getratingsCount());
            }
            return cmp;
        }).collect(Collectors.toList());
    }
}
