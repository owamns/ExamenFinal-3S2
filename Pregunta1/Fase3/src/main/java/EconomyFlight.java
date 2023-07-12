import java.util.List;

public class EconomyFlight extends Flight{
    private List<Passenger> passengers = getPassengers();
    @Override
    public boolean addPassenger(Passenger passenger) {
        passengers.add(passenger);
        return true;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        if (!passenger.isVIP()){
            passengers.remove(passenger);
            return true;
        }
        return false;
    }
}
