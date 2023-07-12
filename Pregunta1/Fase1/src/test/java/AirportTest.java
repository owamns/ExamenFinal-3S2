import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    Flight economyFlight;
    Flight businessFlight;
    Passenger normalPassenger;
    Passenger vipPassenger;

    @BeforeEach
    public void createAirport(){
        economyFlight = new Flight("economy");
        businessFlight = new Flight("business");
        normalPassenger = new Passenger(false);
        vipPassenger = new Passenger(true);
    }

    @Test
    public void testAddNormalPassengerEconomyFlight(){

        economyFlight.addPassenger(normalPassenger);
        List<Passenger> passengers = economyFlight.getPassengers();
        assertTrue(passengers.contains(normalPassenger));
    }

    @Test
    public void testAddVipPassengerEconomyFlight(){

        economyFlight.addPassenger(vipPassenger);
        List<Passenger> passengers = economyFlight.getPassengers();
        assertTrue(passengers.contains(vipPassenger));
    }

    @Test
    public void testAddNormalPassengerBusinessFlight(){

        businessFlight.addPassenger(normalPassenger);
        List<Passenger> passengers = businessFlight.getPassengers();
        assertFalse(passengers.contains(normalPassenger));
    }

    @Test
    public void testAddVipPassengerBusinessFlight(){

        businessFlight.addPassenger(vipPassenger);
        List<Passenger> passengers = businessFlight.getPassengers();
        assertTrue(passengers.contains(vipPassenger));
    }

    @Test
    public void testRemoveNormalPassengerFromEconomyFlight(){

        economyFlight.addPassenger(normalPassenger);
        economyFlight.removePassenger(normalPassenger);
        List<Passenger> passengers = economyFlight.getPassengers();
        assertFalse(passengers.contains(normalPassenger));
    }

    @Test
    public void testRemoveVipPassengerFromEconomyFlight(){

        economyFlight.addPassenger(vipPassenger);
        economyFlight.removePassenger(vipPassenger);
        List<Passenger> passengers = economyFlight.getPassengers();
        assertTrue(passengers.contains(vipPassenger));
    }

    @Test
    public void testRemoveVipPassengerFromBusinessFlight(){

        businessFlight.addPassenger(vipPassenger);
        businessFlight.removePassenger(vipPassenger);
        List<Passenger> passengers = businessFlight.getPassengers();
        assertTrue(passengers.contains(vipPassenger));
    }

}