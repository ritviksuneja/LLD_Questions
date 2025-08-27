package orgfinder;

import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class OrganizationTest {

    private Organization organization;

    @BeforeEach
    public void setUp() {
        organization = new Organization();

        Group root = new Group("Root");
        Group eng = new Group("Engineering");
        Group hr = new Group("HR");
        Group backend = new Group("Backend");
        Group frontend = new Group("Frontend");

        root.addChild(eng);
        root.addChild(hr);
        eng.addChild(backend);
        eng.addChild(frontend);

        organization.addGroup(root);
        organization.addGroup(eng);
        organization.addGroup(hr);
        organization.addGroup(backend);
        organization.addGroup(frontend);

        organization.addEmployee(new Employee("E1", "Alice"), backend);
        organization.addEmployee(new Employee("E2", "Bob"), frontend);
        organization.addEmployee(new Employee("E3", "Charlie"), hr);
    }

    @Test
    public void testLCA_sameGroup() throws EmployeeNotFoundException {
        Group lca = organization.getLCA(List.of(
                new Employee("E1", "Alice"),
                new Employee("E1", "Alice")
        ));
        assertEquals("Backend", lca.getId());
    }

    @Test
    public void testLCA_differentGroupsSameParent() throws EmployeeNotFoundException {
        Group lca = organization.getLCA(List.of(
                new Employee("E1", "Alice"),
                new Employee("E2", "Bob")
        ));
        assertEquals("Engineering", lca.getId());
    }

    @Test
    public void testLCA_crossBranch() throws EmployeeNotFoundException {
        Group lca = organization.getLCA(List.of(
                new Employee("E1", "Alice"),
                new Employee("E3", "Charlie")
        ));
        assertEquals("Root", lca.getId());
    }

    @Test
    public void testLCA_invalidEmployee() {
        Exception ex = assertThrows(EmployeeNotFoundException.class, () -> {
            organization.getLCA(List.of(
                new Employee("E1", "Alice"),
                new Employee("E99", "Ghost")
            ));
        });
        assertTrue(ex.getMessage().contains("E99"));
    }

    @Test
    public void testLCA_singleEmployee() throws EmployeeNotFoundException {
        Group lca = organization.getLCA(List.of(
                new Employee("E3", "Charlie")
        ));
        assertEquals("HR", lca.getId());
    }
}