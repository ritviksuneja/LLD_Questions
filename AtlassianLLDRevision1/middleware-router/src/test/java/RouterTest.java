// Tests:
//   test duplicate route
//   test route does not exist
//   test proper functionality

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.DuplicateRouteException;
import com.example.RouteNotFoundException;
import com.example.Router;
import com.example.RouterImpl;

public class RouterTest {
    private Router router;

    @BeforeEach
    public void setup(){
        router = new RouterImpl();
        router.addRoute("/bar", "barResult");
        router.addRoute("/foo", "fooResult");
        router.addRoute("/foo/bar", "fooBarResult");
    }

    @Test
    public void testDuplicateRoute(){
        DuplicateRouteException ex = assertThrows(DuplicateRouteException.class, () -> router.addRoute("/bar", "duplicateBarResult"));
        assertEquals("Route for path '/bar' already exists!", ex.getMessage());
    }

    @Test
    public void testRouteNotFound(){
        RouteNotFoundException ex = assertThrows(RouteNotFoundException.class, () -> router.callRoute("/barfoo"));
        assertEquals("Route for path '/barfoo' does not exist!", ex.getMessage());
    }

    @Test
    public void testCoreLogic(){
        assertEquals("barResult", router.callRoute("/bar"));
    }
}
