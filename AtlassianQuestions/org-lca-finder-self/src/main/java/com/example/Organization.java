package com.example;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Organization {
    // private final Map<String, Group> groups;
    // private final Map<String, Employee> employees;
    // private final Map<String, Group> employeeToGroup;
    private final Map<String, Set<Group>> employeeToGroups;

    // 1. Add a ReadWriteLock to the class
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    public Organization() {
        // this.groups = new ConcurrentHashMap<>();
        // this.employees = new ConcurrentHashMap<>();
        // this.employeeToGroup = new ConcurrentHashMap<>();
        this.employeeToGroups = new ConcurrentHashMap<>();
    }

    // public void addGroup(Group group){
    //     groups.put(group.getId(), group);
    // }

    // public void addEmployee(Employee employee, Group group){
    //     employees.put(employee.getId(), employee);
    //     employeeToGroup.put(employee.getId(), group);
    // }

    // public Group findLCA(List<Employee> employeeList) throws EmployeeNotFoundException{
    //     Map<String, List<Group>> employeePath = new ConcurrentHashMap<>();

    //     for(Employee employee : employeeList){
    //         if(!this.employees.containsKey(employee.getId())){
    //             throw new EmployeeNotFoundException("Employee with id '" + employee.getId() + "' not found.");
    //         }

    //         Group group = employeeToGroup.get(employee.getId());
    //         List<Group> path = new ArrayList<>();

    //         while(group != null){
    //             path.add(group);
    //             group = group.getParent();
    //         }

    //         Collections.reverse(path);
    //         employeePath.put(employee.getId(), path);
    //     }

    //     List<Group> firstPath = employeePath.values().stream().findFirst().orElse(null);
        
    //     if(firstPath == null){
    //         return null;
    //     }
        
    //     int minPathLength = employeePath.values().stream().mapToInt(List::size).min().orElse(0);
        
    //     Group finalCommon = null;

    //     for(int i = 0; i < minPathLength; i++){
    //         Group currentGroup = firstPath.get(i);
    //         boolean groupExistsInAll = true;

    //         for(List<Group> currentPath : employeePath.values()){
    //             if(!currentPath.get(i).equals(currentGroup)){
    //                 groupExistsInAll = false;
    //                 break;
    //             }
    //         }

    //         if(groupExistsInAll){
    //             finalCommon = currentGroup;
    //         }
    //         else{
    //             break;
    //         }
    //     }

    //     return finalCommon;
    // }

    public void addEmployeeGroupMapping(String emp, Set<Group> groups){
        employeeToGroups.put(emp, groups);
    }

    public Group findLCA(List<String> employeeList) throws EmployeeNotFoundException{
        lock.readLock().lock();
        try{
            if(employeeList.isEmpty()){
                return null;
            }

            Set<Group> commonAncestors = findAncestors(employeeList.get(0));//

            if(commonAncestors.isEmpty()){
                return null;
            }

            for(String employee : employeeList){
                Set<Group> currentAncestors = findAncestors(employee);
                commonAncestors.retainAll(currentAncestors);
            }

            if(commonAncestors.isEmpty()){
                return null;
            }

            for(Group potentialLCA : commonAncestors){
                boolean isLowest = true;

                for(Group child : potentialLCA.getChildren()){
                    if(commonAncestors.contains(child)){
                        isLowest = false;
                        break;
                    }
                }

                if(isLowest){
                    return potentialLCA;
                }
            }        

            return null;
        }
        finally{
            lock.readLock().unlock();
        }
    }

    public Set<Group> findAncestors(String emp){
        if(!employeeToGroups.containsKey(emp)){
            throw new EmployeeNotFoundException("Employee with id '" + emp + "' not found.");
        }

        Queue<Group> queue = new LinkedList<>(employeeToGroups.get(emp));
        Set<Group> ancestors = new HashSet<>();
        Set<Group> visited = new HashSet<>(queue);
        
        while(!queue.isEmpty()){
            Group current = queue.poll();
            ancestors.add(current);

            for(Group parent : current.getParents()){
                if(visited.add(parent)){
                    queue.add(parent);
                }
            }
        }
        return ancestors;
    }
}


