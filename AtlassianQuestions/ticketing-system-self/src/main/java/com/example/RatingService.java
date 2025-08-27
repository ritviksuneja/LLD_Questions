package com.example;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

public class RatingService {
    private final AgentRepository agentRepository;

    public RatingService(AgentRepository agentRepository){
        this.agentRepository = agentRepository;
    }

    public void addRating(String id, int rating){
        agentRepository.getAgent(id).addRating(rating);
    }

    // public List<Agent> getAgentsByAverageRating(){
    //     return agentRepository.getAllAgents().stream()
    //             .sorted(Comparator.comparing(Agent::getAverageRating).reversed().thenComparing(Agent::getTotalRatings, Comparator.reverseOrder())).collect(Collectors.toList());
    // }

    // public List<Agent> getAgentsByAverageRating(){
    //     return agentRepository.getAllAgents().stream()
    //             .sorted((a1, a2) -> {
    //                 int cmp = Double.compare(a2.getAverageRating(), a1.getAverageRating());
    //                 if(cmp == 0){
    //                     return Integer.compare(a2.getTotalRatings(), a1.getTotalRatings());
    //                 }
    //                 return cmp;
    //             }).collect(Collectors.toList());
    // }

    public List<Agent> getBestAgentsOfMonth(YearMonth month){
        return agentRepository.getAllAgents().stream()
                .sorted((a1, a2) -> {
                    int cmp = Double.compare(a2.getAverageRating(month), a1.getAverageRating(month));
                    
                    if(cmp == 0){
                        return Integer.compare(a2.getTotalRatings(month), a1.getTotalRatings(month));
                    }
                    
                    return cmp;
                }).collect(Collectors.toList());
    }
}
