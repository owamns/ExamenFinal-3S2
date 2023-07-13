
import java.util.ArrayList;
import java.util.List;

public class Flight {
    private String flightType;
    private List<Passenger> passengers;
    private String id;

    public Flight(String id, String flightType) {
        this.flightType = flightType;
        this.passengers = new ArrayList<>();
    }

    boolean addPassenger(Passenger passenger){
        if (flightType.equals("business")) {
            if (passenger.isVIP()){
                return passengers.add(passenger);
            }
            return false;
        }
        else if (flightType.equals("economy")){
            return passengers.add(passenger);
        }
        else {
            throw new RuntimeException("Tipo desconocido "+flightType);
        }
    }

    boolean removePassenger(Passenger passenger){
        if (flightType.equals("business")) {
            return false;
        }
        else if (flightType.equals("economy")){
            if (!passenger.isVIP()){
                return passengers.remove(passenger);
            }
            return false;
        }
        else {
            throw new RuntimeException("Tipo desconocido "+flightType);
        }
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }
}
