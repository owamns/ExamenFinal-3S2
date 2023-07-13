import java.util.Set;

public class EconomyFlight extends Flight{
    private Set<Passenger> passengers = getPassengers();

    public EconomyFlight(String id) {
        super(id);
    }

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
