import java.util.List;

public class PremiumFlight extends Flight{
    private List<Passenger> passengers = getPassengers();
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
        if (passengers.contains(passenger)){
            passengers.remove(passenger);
            return true;
        }
        return false;
    }
}
