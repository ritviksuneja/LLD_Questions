package com.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class RatingSystem {
    private final Map<String, Agent> agents;
    private final ReadWriteLock lock;

    public RatingSystem(){
        agents = new HashMap<>();
        lock = new ReentrantReadWriteLock();
    }

    public void addAgent(Agent agent){
        if(agents.containsKey(agent.getId())){
            throw new DuplicateAgentException("Agent with id '" + agent.getId() + "' already exists.");
        }

        lock.writeLock().lock();
        
        try {
            agents.put(agent.getId(), agent);
        } finally{
            lock.writeLock().unlock();
        }
    }

    public void addRating(String agentId, int rating){
        if(!agents.containsKey(agentId)){
            throw new AgentNotFoundException("Agent with id '" + agentId + "' does not exist.");
        }
        if(rating < 1 || rating > 5){
            throw new IllegalArgumentException("Rating provided is out of the allowed range. Please input between 1 and 5.");
        }

        lock.writeLock().lock();

        try {
            agents.get(agentId).addRating(rating);
        } finally{
            lock.writeLock().unlock();
        }
        
    }

    public List<Agent> getAgentsByAverageRating(){
        lock.readLock().lock();

        try {
            if(agents.isEmpty()){
            return Collections.emptyList();
            }

            return agents.values().stream().sorted((a1, a2) -> {
                return Double.compare(a2.getAverageRating(), a1.getAverageRating());
            }).collect(Collectors.toList());
        } finally{
            lock.readLock().unlock();
        } 
    }
}
