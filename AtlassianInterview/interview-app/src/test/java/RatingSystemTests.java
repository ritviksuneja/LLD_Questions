
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Agent;
import com.example.RatingSystem;


// tests:
// 1. test happy scenario.
// 2. test exception when agent is not found
// 3. test exception when there's a duplicate agent already in the RatingSystem
// 4. test when ratings are out of bounds.
// 5. test when there are no agents in the system.

public class RatingSystemTests {
    private RatingSystem ratingSystem;

    @BeforeEach
    public void setup(){
        ratingSystem = new RatingSystem();
        Agent aman = new Agent("1", "Aman");
        Agent biswa = new Agent("2", "Biswa");
        Agent charan = new Agent("3", "Charan");
        ratingSystem.addAgent(aman);
        ratingSystem.addAgent(biswa);
        ratingSystem.addAgent(charan);
    }

    @Test
    public void testCorrectOrderingOfAgents(){
        ratingSystem.addRating("1", 2);
        ratingSystem.addRating("1", 2);
        ratingSystem.addRating("1", 2);
        ratingSystem.addRating("2", 3);
        ratingSystem.addRating("2", 3);
        ratingSystem.addRating("2", 3);
        ratingSystem.addRating("3", 4);
        ratingSystem.addRating("3", 4);
        ratingSystem.addRating("3", 4);

        List<Agent> agents = ratingSystem.getAgentsByAverageRating();

        assertEquals("3", agents.get(0).getId());
    }

    @Test
    public void testCorrectOrderingWhenNoAgents(){
        RatingSystem newRatingSystem = new RatingSystem();
        List<Agent> agents = newRatingSystem.getAgentsByAverageRating();

        assertEquals(0, agents.size());
    }

    @Test
    public void testExceptionWhenAgentIsNotFound(){
        RuntimeException ex = assertThrows(RuntimeException.class, () -> ratingSystem.addRating("4", 1));
        assertEquals("Agent with id '4' does not exist.", ex.getMessage());
    }

    @Test
    public void testExceptionWhenRatingIsIllegal(){
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> ratingSystem.addRating("2", 6));
        assertEquals("Rating provided is out of the acceptable range [1..5].", ex.getMessage());
    }

    @Test
    public void testExceptionWhenAgentAlreadyExists(){
        Agent charu = new Agent("2", "Charu");
        RuntimeException ex = assertThrows(RuntimeException.class, () -> ratingSystem.addAgent(charu));
        assertEquals("Agent with id '2' already exists.", ex.getMessage());
    }
}
