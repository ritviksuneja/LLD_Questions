public class ParkingSpot {
    private final int spotNumber;
    private boolean isAvailable;
    private Vehicle parkedVehicle;
    private final VehicleType vehicleTypeAllowed;
    
    public ParkingSpot(int spotNumber, VehicleType vehicleTypeAllowed){
        this.spotNumber = spotNumber;
        this.isAvailable = true;
        this.parkedVehicle = null;
        this.vehicleTypeAllowed = vehicleTypeAllowed;
    }

    public boolean assignVehicle(Vehicle vehicle) throws Exception{
        if(vehicle.getVehicleType() != this.vehicleTypeAllowed){
            throw new IllegalArgumentException("The type of vehicle which is trying to enter the parking spot is not supported");
        }
        if(!isAvailable){
            throw new Exception("The parking spot is already occupied.");
        }

        this.parkedVehicle = vehicle;
        this.isAvailable = false;
        return true;
    }

    public boolean removeVehicle() throws Exception{
        if(isAvailable){
            throw new Exception("The parking spot doesn't have any vehicle in it.");
        }

        this.parkedVehicle = null;
        this.isAvailable = true;
        return true;
    }

    public int getParkingSpotNumber(){
        return this.spotNumber;
    }

    public boolean isAvailable(){
        return this.isAvailable;
    }

    public Vehicle getParkedVehicle(){
        return this.parkedVehicle;
    }

    public VehicleType getVehicleTypeAllowed(){
        return this.vehicleTypeAllowed;
    }
}
