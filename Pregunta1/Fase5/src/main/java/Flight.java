import java.util.HashSet;
import java.util.Set;

public abstract class Flight {
    private Set<Passenger> passengers;
    private String id;

    public Flight(String id) {
        this.id = id;
        this.passengers = new HashSet<>();
    }

    public abstract boolean addPassenger(Passenger passenger);

    public abstract boolean removePassenger(Passenger passenger);

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public String getId() {
        return id;
    }
}
