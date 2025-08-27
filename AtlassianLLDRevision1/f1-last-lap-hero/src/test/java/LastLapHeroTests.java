
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Driver;
import com.example.DriverNotFoundException;
import com.example.Lap;
import com.example.LastLapHero;

public class LastLapHeroTests {
    private LastLapHero lastLapHero;

    @BeforeEach
    public void setup(){
        lastLapHero = new LastLapHero();
        Driver aman = new Driver("Aman", "1");
        Driver biswa = new Driver("Biswa", "2");
        Driver charan = new Driver("Charan", "3");
        lastLapHero.addDriver(aman);
        lastLapHero.addDriver(biswa);
        lastLapHero.addDriver(charan);
    }

    @Test
    public void testHappyScenario(){
        lastLapHero.addLap("1", new Lap(1000));
        lastLapHero.addLap("1", new Lap(1000));
        lastLapHero.addLap("1", new Lap(1000));
        lastLapHero.addLap("2", new Lap(1000));
        lastLapHero.addLap("2", new Lap(1000));
        lastLapHero.addLap("2", new Lap(1000));
        lastLapHero.addLap("3", new Lap(1000));
        lastLapHero.addLap("3", new Lap(1000));
        lastLapHero.addLap("3", new Lap(2000));
        String heroId = lastLapHero.getLastLapHero();
        assertEquals("3", heroId);
    }

    @Test
    public void testDriverNotFoundException(){
        DriverNotFoundException ex = assertThrows(DriverNotFoundException.class, () -> lastLapHero.addLap("10", new Lap(1000)));
        assertEquals("Driver with id '10' does not exist!", ex.getMessage());
    }
}
