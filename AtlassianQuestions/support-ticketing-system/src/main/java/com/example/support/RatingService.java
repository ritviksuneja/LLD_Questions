package com.example.support;

import java.util.List;
import java.util.stream.Collectors;

public class RatingService {
    private final AgentRepository agentRepository;

    public RatingService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    public void addRating(String agentId, int rating) {
        Agent agent = agentRepository.getAgent(agentId);
        agent.addRating(rating);
    }

    public List<Agent> getAgentsByAverageRating() {
        return agentRepository.getAllAgents()
                .stream()
                .sorted((a1, a2) -> Double.compare(a2.getAverageRating(), a1.getAverageRating()))
                .collect(Collectors.toList());
    }
}