import java.util.ArrayList;
import java.util.List;

public class Level {
    private final List<ParkingSpot> parkingSpots;
    private final int levelNumber;

    public Level(int levelNumber, int numSpots){
        this.levelNumber = levelNumber;
        this.parkingSpots = new ArrayList<>();

        // Assign spots in ration of 50:40:10 for bikes, cars and trucks
        double spotsForBikes = 0.50;
        double spotsForCars = 0.40;

        int numBikes = (int) (numSpots * spotsForBikes);
        int numCars = (int) (numSpots * spotsForCars);

        for (int i = 1; i <= numBikes; i++) {
            parkingSpots.add(new ParkingSpot(i,VehicleType.MOTORCYCLE));
        }
        for (int i = numBikes + 1; i <= numBikes + numCars; i++) {
            parkingSpots.add(new ParkingSpot(i,VehicleType.CAR));
        }
        for (int i = numBikes + numCars + 1; i <= numSpots; i++) {
            parkingSpots.add(new ParkingSpot(i,VehicleType.TRUCK));
        }
    }

    public boolean parkVehicle(Vehicle vehicle) throws Exception{
        ParkingSpot ps = null;
        
        for(ParkingSpot spot : parkingSpots){
            if(spot.getVehicleTypeAllowed() == vehicle.getVehicleType() && spot.isAvailable()){
                ps = spot;
                break;
            }
        }

        if(ps != null){
            ps.assignVehicle(vehicle);
            return true;
        }

        return false;
    }

    public boolean unparkVehicle(Vehicle vehicle) throws Exception{
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
