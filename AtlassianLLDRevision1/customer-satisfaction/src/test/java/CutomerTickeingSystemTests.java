// tests->
// 1. trying to add an illegal rating.
// 2. trying to rate an agent which is not in the system.
// 3. testing if there's only one agent.
// 4. testing properly.

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Agent;
import com.example.AgentNotFoundException;
import com.example.CustomerTicketingSystem;
import com.example.IllegalRatingException;

public class CutomerTickeingSystemTests {
    CustomerTicketingSystem ticketingSystem;
    
    //before each vs before all is tricky
    @BeforeEach
    public void setup(){
        ticketingSystem = new CustomerTicketingSystem();
        ticketingSystem.addAgent("1", "Aman");
        ticketingSystem.addAgent("2", "Biswa");
        ticketingSystem.addAgent("3", "Charan");
    }

    //tricky
    @Test
    public void testAddingIllegalRating(){
        IllegalRatingException ex = assertThrows(IllegalRatingException.class, () -> ticketingSystem.addRating("1", -1));
        assertEquals("The rating should be between in the range [0,5]", ex.getMessage());
    }

    @Test
    public void testRatingIllegalAgent(){
        AgentNotFoundException ex = assertThrows(AgentNotFoundException.class, () -> ticketingSystem.addRating("agentId", 20));
        assertEquals("Agent with id 'agentId' not found!", ex.getMessage());
    }

    @Test
    public void testCorrectnessOfLogic(){
        ticketingSystem.addRating("1", 3);
        ticketingSystem.addRating("1", 3);
        ticketingSystem.addRating("1", 3);
        ticketingSystem.addRating("1", 3);
        ticketingSystem.addRating("2", 4);
        ticketingSystem.addRating("2", 4);
        ticketingSystem.addRating("2", 4);
        ticketingSystem.addRating("2", 4);
        ticketingSystem.addRating("3", 5);
        ticketingSystem.addRating("3", 5);
        ticketingSystem.addRating("3", 5);
        ticketingSystem.addRating("3", 5);
        ticketingSystem.addRating("3", 5);
        List<Agent> agentsByAverageRating = ticketingSystem.getAgentsByAverageRating();
        assertEquals("1", agentsByAverageRating.get(2).getId());
    }

    @Test
    public void testCorrectnessOfLogicIfSingleAgent(){
        CustomerTicketingSystem newTicketingSystem = new CustomerTicketingSystem();
        newTicketingSystem.addAgent("5", "Ritvik");
        newTicketingSystem.addAgent("5", "Ritvik");
        newTicketingSystem.addAgent("5", "Ritvik");
        newTicketingSystem.addAgent("5", "Ritvik");
        newTicketingSystem.addAgent("5", "Ritvik");
        List<Agent> agentsByAverageRating = newTicketingSystem.getAgentsByAverageRating();
        assertEquals("5", agentsByAverageRating.get(0).getId());
    }

    @Test
    public void testCorrectnessOfLogicWhenSameAverage(){
        ticketingSystem.addRating("1", 3);
        ticketingSystem.addRating("1", 3);
        ticketingSystem.addRating("1", 3);
        ticketingSystem.addRating("2", 3);
        ticketingSystem.addRating("2", 3);
        ticketingSystem.addRating("2", 3);
        ticketingSystem.addRating("2", 3);
        List<Agent> agentsByAverageRating = ticketingSystem.getAgentsByAverageRating();
        assertEquals("1", agentsByAverageRating.get(1).getId());
    }
}
