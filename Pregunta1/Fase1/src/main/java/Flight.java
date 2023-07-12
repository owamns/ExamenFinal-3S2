
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String flightType;
    private List<Passenger> passengers;

    public Flight(String flightType) {
        this.flightType = flightType;
        this.passengers = new ArrayList<>();
    }

    boolean addPassenger(Passenger passenger){
        if (isValidPassenger(passenger)) {
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    boolean removePassenger(Passenger passenger){
        if (!passenger.isVIP() && passengers.contains(passenger)) {
            passengers.remove(passenger);
            return true;
        }
        return false;
    }

    private boolean isValidPassenger(Passenger passenger){
        if (flightType.equals("business") && passenger.isVIP()) {
            return true;
        }
        else if (flightType.equals("economy")){
            return true;
        }
        return false;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

}
