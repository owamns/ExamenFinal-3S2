import java.util.Set;

public class BusinessFlight extends Flight{
    private Set<Passenger> passengers = getPassengers();

    public BusinessFlight(String id) {
        super(id);
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        if (passenger.isVIP()){
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    @Override
    public boolean removePassenger(Passenger passenger) {
        return false;
    }
}
