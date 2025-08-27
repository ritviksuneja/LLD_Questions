package orgfinder;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String id) {
        super("Employee not found: " + id);
    }
}