
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.example.Employee;
import com.example.EmployeeNotFoundException;
import com.example.Group;
import com.example.Organization;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrganizationTest {
    private Organization organization;
    private Employee aman;
    private Employee ben;
    private Employee charan;
    private Group root;
    private Group hr;
    private Group finance;
    private Group hiring;
    private Group screening;

    @BeforeAll
    public void setup(){
        organization = new Organization();
        aman = new Employee("1", "Aman");
        ben = new Employee("2", "Ben");
        charan = new Employee("3", "Charan");

        root = new Group("1");
        hr = new Group("2");
        finance = new Group("3");
        hiring = new Group("4");
        screening = new Group("5");

        root.addChild(hr);
        root.addChild(finance);
        hr.addChild(hiring);
        hr.addChild(screening);
        
        // organization.addGroup(root);
        // organization.addGroup(hr);
        // organization.addGroup(finance);
        // organization.addGroup(screening);
        // organization.addGroup(hiring);

        // organization.addEmployee(ben, hiring);
        // organization.addEmployee(charan, screening);
        // organization.addEmployee(aman, finance);
    }

    @Test
    public void testSameGroupLCA(){
        Group lcaGroup = organization.findLCA(List.of(ben, charan));
        assertEquals(hr.getId(), lcaGroup.getId());
    }

    @Test
    public void testDifferentGroupsLCA(){
        Group lcaGroup = organization.findLCA(List.of(ben, aman));
        assertEquals(root.getId(), lcaGroup.getId());
    }

    @Test 
    public void testInvalidEmployeeLCA(){
        Employee zlatan = new Employee("4", "Zlatan");

        EmployeeNotFoundException ex = assertThrows(EmployeeNotFoundException.class, () -> {
            organization.findLCA(List.of(ben, zlatan));
        });

        assertEquals("Employee with id '4' not found.", ex.getMessage());   
    }
}
