package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AgentRepository {
    private final Map<String, Agent> agents;

    public AgentRepository(){
        this.agents = new ConcurrentHashMap<>();
    }
    

    public void addAgent(Agent agent){
        agents.put(agent.getId(), agent);
    }

    public Agent getAgent(String id){
        if(!agents.containsKey(id)){
            throw new AgentNotFoundException("Agent with id '" + id + "' doesn't exist.");
        }

        return agents.get(id);
    }

    public void removeAgent(String id){
        if(!agents.containsKey(id)){
            throw new AgentNotFoundException("Agent with id '" + id + "' doesn't exist.");
        }

        agents.remove(id);
        System.out.println("Agent removed successfully!");
    }

    public List<Agent> getAllAgents(){
        return new ArrayList<>(agents.values());
    }
}
