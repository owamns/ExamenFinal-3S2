import java.util.List;

public class BusinessFlight extends Flight{
    private List<Passenger> passengers = getPassengers();

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
