package com.example.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.ArrayList;

public class AgentRepository {
    private final Map<String, Agent> agents = new ConcurrentHashMap<>();

    public void addAgent(Agent agent) {
        agents.put(agent.getId(), agent);
    }

    public Agent getAgent(String id) {
        Agent agent = agents.get(id);
        if (agent == null) {
            throw new AgentNotFoundException("Agent with ID " + id + " not found");
        }
        return agent;
    }

    public List<Agent> getAllAgents() {
        return new ArrayList<>(agents.values());
    }
}