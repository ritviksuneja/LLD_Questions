
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Agent;
import com.example.AgentNotFoundException;
import com.example.AgentRepository;
import com.example.InvalidRatingException;
import com.example.RatingService;

public class RatingServiceTest {
    private AgentRepository agentRepository;
    private RatingService ratingService;

    @BeforeEach
    public void setup(){
        agentRepository = new AgentRepository();
        
        Agent alice = new Agent("1", "alice");
        Agent bob = new Agent("2", "bob");
        Agent charlie = new Agent("3", "charlie");

        agentRepository.addAgent(alice);
        agentRepository.addAgent(bob);
        agentRepository.addAgent(charlie);

        ratingService = new RatingService(agentRepository);
    }

    @Test
    public void testValidRating(){
        ratingService.addRating("1", 5);
        assertEquals(5.0, agentRepository.getAgent("1").getAverageRating(YearMonth.now()));
    }

    @Test
    public void testAddInvalidRatingThrowsException(){
        InvalidRatingException ex = assertThrows(InvalidRatingException.class, () -> ratingService.addRating("1", 0));
        assertEquals("Invalid rating provided. The rating must be in the range [1,5].", ex.getMessage());
    }

    @Test
    public void testAddRatingThrowsAgentNotFoundException(){
        AgentNotFoundException ex = assertThrows(AgentNotFoundException.class, () -> ratingService.addRating("4", 1));
        assertEquals("Agent with id '4' doesn't exist.", ex.getMessage());
    }

    @Test
    public void testGetAgentsByAverageRating(){
        ratingService.addRating("1", 5);
        ratingService.addRating("1", 4);
        ratingService.addRating("2", 3);
        ratingService.addRating("3", 1);

        List<Agent> agents = ratingService.getBestAgentsOfMonth(YearMonth.now());
        assertEquals("1", agents.get(0).getId());
    }
}
