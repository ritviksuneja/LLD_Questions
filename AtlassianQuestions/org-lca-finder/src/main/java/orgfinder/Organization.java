package orgfinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Organization {
    private final Map<String, Group> groups = new HashMap<>();
    private final Map<String, Employee> employees = new HashMap<>();
    private final Map<String, Group> employeeToGroup = new HashMap<>();

    public void addGroup(Group group) {
        groups.put(group.getId(), group);
    }

    public void addEmployee(Employee employee, Group group) {
        employees.put(employee.getId(), employee);
        employeeToGroup.put(employee.getId(), group);
    }

    public Group getLCA(List<Employee> employeeList) throws EmployeeNotFoundException {
        if (employeeList == null || employeeList.isEmpty()) return null;

        Map<String, List<Group>> paths = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(employeeList.size());
        List<Callable<Void>> tasks = new ArrayList<>();

        for (Employee emp : employeeList) {
            tasks.add(() -> {
                Group group = employeeToGroup.get(emp.getId());
                if (group == null) throw new EmployeeNotFoundException(emp.getId());
                List<Group> path = new ArrayList<>();
                while (group != null) {
                    path.add(group);
                    group = group.getParent();
                }
                Collections.reverse(path);
                paths.put(emp.getId(), path);
                return null;
            });
        }

        try {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread interrupted", e);
        } finally {
            executor.shutdown();
        }

        List<Group> firstPath = paths.values().iterator().next();
        int minLen = paths.values().stream().mapToInt(List::size).min().orElse(0);
        Group lastCommon = null;

        for (int i = 0; i < minLen; i++) {
            final int k = i;
            Group candidate = firstPath.get(i);
            boolean allMatch = paths.values().stream().allMatch(path -> path.get(k).equals(candidate));
            if (allMatch) {
                lastCommon = candidate;
            } else {
                break;
            }
        }

        return lastCommon;
    }
}