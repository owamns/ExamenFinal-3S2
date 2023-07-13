
import java.util.ArrayList;
import java.util.List;

public abstract class Flight {
    private List<Passenger> passengers;
    private String id;

    public Flight(String id) {
        this.id = id;
        this.passengers = new ArrayList<>();
    }

    public abstract boolean addPassenger(Passenger passenger);

    public abstract boolean removePassenger(Passenger passenger);

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public String getId() {
        return id;
    }
}
