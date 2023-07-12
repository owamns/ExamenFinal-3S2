
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
        if (flightType.equals("business") && passenger.isVIP()) {
            passengers.add(passenger);
            return true;
        }
        else if (flightType.equals("economy")){
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    boolean removePassenger(Passenger passenger){
        if (!passenger.isVIP()) {
            passengers.remove(passenger);
            return true;
        }
        return false;
    }
}
