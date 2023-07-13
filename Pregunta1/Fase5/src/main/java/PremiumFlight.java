import java.util.Set;

public class PremiumFlight extends Flight{
    private Set<Passenger> passengers = getPassengers();

    public PremiumFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passenger.isVIP()){
            return passengers.add(passenger);
        }
        return false;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        if (passengers.contains(passenger)){
            return passengers.remove(passenger);
        }
        return false;
    }
}
