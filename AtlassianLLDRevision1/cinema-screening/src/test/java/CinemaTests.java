import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Cinema;
import com.example.Movie;
import com.example.Screening;

public class CinemaTests {
    private Cinema cinema;

    @BeforeEach
    public void setup(){
        cinema = new Cinema(600, 1380);
        cinema.addScreening(new Screening(new Movie("Sholay", 200), 600));
        cinema.addScreening(new Screening(new Movie("Pushpa", 200), 800));
        cinema.addScreening(new Screening(new Movie("War", 200), 1000));
    }

    @Test
    public void testHappyScenario(){
        assertEquals(true, cinema.addScreening(new Screening(new Movie("Ritvik's biopic", 100), 1200)));
    }
}
