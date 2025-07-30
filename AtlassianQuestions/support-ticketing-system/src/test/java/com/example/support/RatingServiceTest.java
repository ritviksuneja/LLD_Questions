package com.example.support;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RatingServiceTest {

    private AgentRepository agentRepository;
    private RatingService ratingService;

    @BeforeEach
    public void setup() {
        agentRepository = new AgentRepository();
        agentRepository.addAgent(new Agent("1", "Alice"));
        agentRepository.addAgent(new Agent("2", "Bob"));
        agentRepository.addAgent(new Agent("3", "Charlie"));
        ratingService = new RatingService(agentRepository);
    }

    @Test
    public void testAddValidRating() {
        ratingService.addRating("1", 5);
        Agent alice = agentRepository.getAgent("1");
        assertEquals(5.0, alice.getAverageRating());
    }

    @Test
    public void testAddInvalidRatingThrowsException() {
        assertThrows(InvalidRatingException.class, () -> {
            ratingService.addRating("1", 6);
        });
    }

    @Test
    public void testAgentNotFoundThrowsException() {
        assertThrows(AgentNotFoundException.class, () -> {
            ratingService.addRating("999", 4);
        });
    }

    @Test
    public void testGetAgentsByAverageRating() {
        ratingService.addRating("1", 5);
        ratingService.addRating("1", 4);
        ratingService.addRating("2", 3);
        ratingService.addRating("3", 1);

        List<Agent> sorted = ratingService.getAgentsByAverageRating();
        assertEquals("Alice", sorted.get(0).getName());
        assertEquals("Bob", sorted.get(1).getName());
        assertEquals("Charlie", sorted.get(2).getName());
    }

    @Test
    public void testThreadSafety() throws InterruptedException {
        int threads = 10;
        int ratingsPerThread = 10;
        CountDownLatch latch = new CountDownLatch(threads);

        Runnable task = () -> {
            for (int i = 0; i < ratingsPerThread; i++) {
                ratingService.addRating("1", 5);
            }
            latch.countDown();
        };

        for (int i = 0; i < threads; i++) {
            new Thread(task).start();
        }

        latch.await();

        Agent alice = agentRepository.getAgent("1");
        assertEquals(5.0, alice.getAverageRating());
        assertEquals(threads * ratingsPerThread, alice.getAverageRating() * alice.getAverageRating() / 5.0);
    }
}