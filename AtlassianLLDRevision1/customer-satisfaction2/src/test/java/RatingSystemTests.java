
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Agent;
import com.example.AgentNotFoundException;
import com.example.RatingSystem;

// Tests:
// 1. Test happy scenario
// 2. Test agent not found Exceptions
// 3. Test duplicate agent found Exceptions
// 4. Test adding illegal rating
// 5. Test adding an agent with no id
// 6. Edge case 1 - Test only one agent
// 7. Edge case 2 - Test when there are no agents

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
    public void testHappyScenario(){
        ratingSystem.addRating("1", 2);
        ratingSystem.addRating("1", 2);
        ratingSystem.addRating("1", 2);
        ratingSystem.addRating("2", 3);
        ratingSystem.addRating("2", 3);
        ratingSystem.addRating("2", 3);
        ratingSystem.addRating("3", 4);
        ratingSystem.addRating("3", 4);
        ratingSystem.addRating("3", 4);
        
        List<Agent> sortedAgents = ratingSystem.getAgentsByAverageRating();
        
        assertEquals("3", sortedAgents.get(0).getId());
    }

    @Test
    public void testAgentNotFoundException(){
        AgentNotFoundException ex = assertThrows(AgentNotFoundException.class, () -> ratingSystem.addRating("4", 3));
        assertEquals("Agent with id '4' does not exist.", ex.getMessage());
    }

    
}
