### Improved Design: Dynamic Allocation Using a Configuration Map

Instead of hardcoding the allocation ratios in the `allocateSpots` method, you can use a `Map<VehicleType, Double>` to define the allocation percentages dynamically. This map can be passed to the `ParkingSpotAllocator` at runtime, making the design more flexible and extensible.

---

#### 1. **Update `ParkingSpotAllocator` Interface**
Modify the interface to accept a configuration map for allocation.

```java
import java.util.List;
import java.util.Map;

public interface ParkingSpotAllocator {
    List<ParkingSpot> allocateSpots(int numSpots, Map<VehicleType, Double> allocationRatios);
}
```

---

#### 2. **Implement a Generic Allocator**
Create an implementation of `ParkingSpotAllocator` that uses the configuration map to allocate spots dynamically.

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericParkingSpotAllocator implements ParkingSpotAllocator {

    @Override
    public List<ParkingSpot> allocateSpots(int numSpots, Map<VehicleType, Double> allocationRatios) {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        int currentSpotId = 1;

        for (Map.Entry<VehicleType, Double> entry : allocationRatios.entrySet()) {
            VehicleType vehicleType = entry.getKey();
            int spotsForType = (int) (numSpots * entry.getValue());

            for (int i = 0; i < spotsForType; i++) {
                parkingSpots.add(new ParkingSpot(currentSpotId++, vehicleType));
            }
        }

        // Handle any remaining spots (e.g., due to rounding issues)
        while (parkingSpots.size() < numSpots) {
            parkingSpots.add(new ParkingSpot(currentSpotId++, VehicleType.CAR)); // Default to CAR
        }

        return parkingSpots;
    }
}
```

---

#### 3. **Modify the `Level` Class**
Refactor the `Level` class to use the `GenericParkingSpotAllocator` and pass the allocation ratios dynamically.

```java
import java.util.List;
import java.util.Map;

public class Level {
    private final List<ParkingSpot> parkingSpots;
    private final int levelNumber;

    public Level(int levelNumber, int numSpots, ParkingSpotAllocator allocator, Map<VehicleType, Double> allocationRatios) {
        this.levelNumber = levelNumber;
        this.parkingSpots = allocator.allocateSpots(numSpots, allocationRatios);
    }

    public boolean parkVehicle(Vehicle vehicle) throws Exception {
        ParkingSpot ps = null;

        for (ParkingSpot spot : parkingSpots) {
            if (spot.getVehicleTypeAllowed() == vehicle.getVehicleType() && spot.isAvailable()) {
                ps = spot;
                break;
            }
        }

        if (ps != null) {
            ps.assignVehicle(vehicle);
            return true;
        }

        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) throws Exception {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isAvailable() && spot.getParkedVehicle().equals(vehicle)) {
                spot.removeVehicle();
                return true;
            }
        }
        return false;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
```

---

#### 4. **Define Allocation Ratios Dynamically**
When creating a `Level`, you can define the allocation ratios dynamically using a `Map`.

```java
import java.util.HashMap;
import java.util.Map;

public class ParkingLotApp {
    public static void main(String[] args) {
        ParkingSpotAllocator allocator = new GenericParkingSpotAllocator();

        // Define allocation ratios dynamically
        Map<VehicleType, Double> allocationRatios = new HashMap<>();
        allocationRatios.put(VehicleType.MOTORCYCLE, 0.50);
        allocationRatios.put(VehicleType.CAR, 0.40);
        allocationRatios.put(VehicleType.TRUCK, 0.10);

        // Create a level with dynamic allocation
        Level level1 = new Level(1, 100, allocator, allocationRatios);

        // Add a new vehicle type without modifying Level or Allocator
        allocationRatios.put(VehicleType.ELECTRIC_CAR, 0.20);
        Level level2 = new Level(2, 50, allocator, allocationRatios);
    }
}
```

---

### Benefits of This Design:
1. **No Need to Modify `allocateSpots`**:  
   - The allocation logic is generic and works for any number of vehicle types as long as the configuration map is updated.

2. **Open/Closed Principle**:  
   - Adding a new vehicle type (e.g., `ELECTRIC_CAR`) only requires updating the configuration map, not modifying the `Level` or `ParkingSpotAllocator` classes.

3. **Dynamic Behavior**:  
   - The allocation ratios can be loaded from a configuration file, database, or user input, making the system highly configurable.

4. **Scalability**:  
   - The design scales well as new vehicle types or allocation strategies are introduced.

---

### Alternative: Factory-Based Allocation
If you want even more flexibility, you can use a factory to create parking spots for each vehicle type. This approach is useful if the creation of parking spots involves complex logic or dependencies.

Let me know if you'd like an example of this approach!

ritviksuneja: update readme.md file with the things you meantioned in the last answer.

GitHub Copilot: Here’s an updated README.md file that explains the improved design for the `Level` class and the dynamic allocation strategy:

```markdown
# Parking Lot System

This project implements a parking lot system with a focus on scalability, flexibility, and adherence to SOLID principles. The `Level` class has been designed to dynamically allocate parking spots for different vehicle types, making it easy to add new vehicle types without modifying the core logic.

---

## Key Features

1. **Dynamic Parking Spot Allocation**:
   - Parking spots are allocated based on a configurable ratio for different vehicle types.
   - The allocation logic is decoupled from the `Level` class, adhering to the **Open/Closed Principle**.

2. **Extensible Design**:
   - New vehicle types can be added by simply updating the allocation configuration or creating a new `ParkingSpotAllocator` implementation.
   - No changes are required in the `Level` class for new vehicle types.

3. **Single Responsibility Principle**:
   - The `Level` class is responsible only for managing parking spots, while the allocation logic is handled by a separate `ParkingSpotAllocator` interface.

---

## Design Overview

### 1. `ParkingSpotAllocator` Interface
The `ParkingSpotAllocator` interface defines the contract for allocating parking spots. It allows different allocation strategies to be implemented without modifying the `Level` class.

```java
public interface ParkingSpotAllocator {
    List<ParkingSpot> allocateSpots(int numSpots, Map<VehicleType, Double> allocationRatios);
}
```

---

### 2. `GenericParkingSpotAllocator` Implementation
The `GenericParkingSpotAllocator` dynamically allocates parking spots based on a configuration map (`Map<VehicleType, Double>`). This makes the allocation logic flexible and extensible.

```java
public class GenericParkingSpotAllocator implements ParkingSpotAllocator {

    @Override
    public List<ParkingSpot> allocateSpots(int numSpots, Map<VehicleType, Double> allocationRatios) {
        List<ParkingSpot> parkingSpots = new ArrayList<>();
        int currentSpotId = 1;

        for (Map.Entry<VehicleType, Double> entry : allocationRatios.entrySet()) {
            VehicleType vehicleType = entry.getKey();
            int spotsForType = (int) (numSpots * entry.getValue());

            for (int i = 0; i < spotsForType; i++) {
                parkingSpots.add(new ParkingSpot(currentSpotId++, vehicleType));
            }
        }

        // Handle any remaining spots (e.g., due to rounding issues)
        while (parkingSpots.size() < numSpots) {
            parkingSpots.add(new ParkingSpot(currentSpotId++, VehicleType.CAR)); // Default to CAR
        }

        return parkingSpots;
    }
}
```

---

### 3. `Level` Class
The `Level` class uses the `ParkingSpotAllocator` to allocate parking spots dynamically. It no longer contains hardcoded logic for specific vehicle types.

```java
public class Level {
    private final List<ParkingSpot> parkingSpots;
    private final int levelNumber;

    public Level(int levelNumber, int numSpots, ParkingSpotAllocator allocator, Map<VehicleType, Double> allocationRatios) {
        this.levelNumber = levelNumber;
        this.parkingSpots = allocator.allocateSpots(numSpots, allocationRatios);
    }

    public boolean parkVehicle(Vehicle vehicle) throws Exception {
        ParkingSpot ps = null;

        for (ParkingSpot spot : parkingSpots) {
            if (spot.getVehicleTypeAllowed() == vehicle.getVehicleType() && spot.isAvailable()) {
                ps = spot;
                break;
            }
        }

        if (ps != null) {
            ps.assignVehicle(vehicle);
            return true;
        }

        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) throws Exception {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isAvailable() && spot.getParkedVehicle().equals(vehicle)) {
                spot.removeVehicle();
                return true;
            }
        }
        return false;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
```

---

### 4. Dynamic Allocation Ratios
The allocation ratios for different vehicle types can be defined dynamically using a `Map<VehicleType, Double>`. For example:

```java
Map<VehicleType, Double> allocationRatios = new HashMap<>();
allocationRatios.put(VehicleType.MOTORCYCLE, 0.50);
allocationRatios.put(VehicleType.CAR, 0.40);
allocationRatios.put(VehicleType.TRUCK, 0.10);
```

This map can be passed to the `Level` class during initialization.

---

## Example Usage

```java
public class ParkingLotApp {
    public static void main(String[] args) {
        ParkingSpotAllocator allocator = new GenericParkingSpotAllocator();

        // Define allocation ratios dynamically
        Map<VehicleType, Double> allocationRatios = new HashMap<>();
        allocationRatios.put(VehicleType.MOTORCYCLE, 0.50);
        allocationRatios.put(VehicleType.CAR, 0.40);
        allocationRatios.put(VehicleType.TRUCK, 0.10);

        // Create a level with dynamic allocation
        Level level1 = new Level(1, 100, allocator, allocationRatios);

        // Add a new vehicle type without modifying Level or Allocator
        allocationRatios.put(VehicleType.ELECTRIC_CAR, 0.20);
        Level level2 = new Level(2, 50, allocator, allocationRatios);
    }
}
```

---

## Benefits of the Design

1. **Flexibility**: Easily add new vehicle types or change allocation ratios without modifying existing code.
2. **Scalability**: The system can handle a large number of vehicle types and parking levels.
3. **Adherence to SOLID Principles**:
   - **Open/Closed Principle**: The `Level` class is open for extension but closed for modification.
   - **Single Responsibility Principle**: The `Level` class is focused on managing parking spots, while allocation logic is handled by the `ParkingSpotAllocator`.

---

## Future Improvements

1. **External Configuration**:
   - Load allocation ratios from a configuration file or database to make the system even more dynamic.
2. **Custom Allocation Strategies**:
   - Implement additional `ParkingSpotAllocator` strategies for specific use cases (e.g., prioritizing electric vehicles).

---

This design ensures that the parking lot system is robust, maintainable, and ready for future enhancements.
```

ritviksuneja: which design pattern is used in generating ParkingSpotAllocator and GenericParkingSpotAllocator? Or is there no design pattern used there?

GitHub Copilot: The design involving `ParkingSpotAllocator` and `GenericParkingSpotAllocator` can be associated with the **Strategy Pattern**. Here's why:

### **Strategy Pattern**
The **Strategy Pattern** is used when you want to define a family of algorithms (or strategies), encapsulate each one, and make them interchangeable. It allows the behavior of a class to be selected at runtime without modifying the class itself.

In this case:
- **Context**: The `Level` class acts as the context that uses a `ParkingSpotAllocator` to allocate parking spots.
- **Strategy Interface**: The `ParkingSpotAllocator` interface defines the contract for different allocation strategies.
- **Concrete Strategies**: The `GenericParkingSpotAllocator` (and potentially other allocators) are concrete implementations of the `ParkingSpotAllocator` interface, each providing a specific allocation strategy.

This design allows you to dynamically choose or extend the allocation logic without modifying the `Level` class, adhering to the **Open/Closed Principle**.

---

### Why It Fits the Strategy Pattern
1. **Encapsulation of Behavior**: The allocation logic is encapsulated in the `ParkingSpotAllocator` implementations, not in the `Level` class.
2. **Interchangeability**: You can swap out `GenericParkingSpotAllocator` for another implementation (e.g., `ElectricCarParkingSpotAllocator`) without changing the `Level` class.
3. **Runtime Flexibility**: The allocation strategy can be chosen dynamically at runtime by passing the desired `ParkingSpotAllocator` implementation to the `Level` constructor.

---

### If No Design Pattern Was Used
If the allocation logic were hardcoded directly into the `Level` class (as in the original code), no design pattern would be applied. This would tightly couple the allocation logic to the `Level` class, making it harder to extend or modify.

By introducing the `ParkingSpotAllocator` abstraction, the design becomes more flexible and adheres to the principles of the **Strategy Pattern**.