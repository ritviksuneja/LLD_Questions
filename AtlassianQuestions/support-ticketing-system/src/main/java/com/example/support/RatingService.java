package com.example.support;

import java.util.Comparator;
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
        // return agentRepository.getAllAgents()
        //         .stream()
        //         .sorted((a1, a2) -> {
        //             int cmp = Double.compare(a2.getAverageRating(), a1.getAverageRating());
        //             if(cmp == 0){
        //                 return Integer.compare(a2.getTotalRatingsCount(), a1.getTotalRatingsCount());
        //             }
        //             return cmp;
        //         })
        //         .collect(Collectors.toList());

        return agentRepository.getAllAgents()
                .stream()
                .sorted(Comparator.comparing(Agent::getAverageRating).reversed()
                        .thenComparing(Agent::getTotalRatingsCount))
                .collect(Collectors.toList());
    }
}