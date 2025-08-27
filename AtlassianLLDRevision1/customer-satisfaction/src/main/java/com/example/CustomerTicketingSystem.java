package com.example;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CustomerTicketingSystem {
    private final Map<String, Agent> agents;

    public CustomerTicketingSystem(){
        this.agents = new ConcurrentHashMap<>();
    }

    public void addAgent(String id, String name){
        agents.put(id, new Agent(id, name));
    }

    public void addRating(String agentId, int rating){
        if(!agents.containsKey(agentId)){
            throw new AgentNotFoundException("Agent with id '" + agentId + "' not found!");
        }

        agents.get(agentId).addRating(rating);
    }

    //tricky
    public List<Agent> getAgentsByAverageRating(){
        return agents.values().stream().sorted( (a1, a2) -> {
            int cmp = Double.compare(a2.getAverageRating(), a1.getAverageRating());
            if(cmp == 0){
                return Integer.compare(a2.getTotalRatings(), a1.getTotalRatings());
            }
            return cmp;
        }).collect(Collectors.toList());
    }
}
