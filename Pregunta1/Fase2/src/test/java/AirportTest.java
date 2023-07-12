import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class AirportTest {

    @Test
    public void testAddPassengersEconomyFlight(){
        EconomyFlight economyFlight = new EconomyFlight();

        Passenger normalPassenger = new Passenger(false);
        Passenger vipPassenger = new Passenger(true);

        economyFlight.addPassenger(normalPassenger);
        economyFlight.addPassenger(vipPassenger);

        assertTrue(economyFlight.getPassengers().contains(normalPassenger));
        assertTrue(economyFlight.getPassengers().contains(vipPassenger));
    }

    @Test
    public void testAddNormalPassengerBusinessFlight(){
        BusinessFlight businessFlight = new BusinessFlight();

        Passenger normalPassenger = new Passenger(false);

        businessFlight.addPassenger(normalPassenger);
        List<Passenger> passengers = businessFlight.getPassengers();
        assertFalse(passengers.contains(normalPassenger));
    }

    @Test
    public void testAddVipPassengerBusinessFlight(){
        BusinessFlight businessFlight = new BusinessFlight();

        Passenger vipPassenger = new Passenger(true);

        businessFlight.addPassenger(vipPassenger);
        List<Passenger> passengers = businessFlight.getPassengers();
        assertTrue(passengers.contains(vipPassenger));
    }

    @Test
    public void testRemoveNormalPassengerFromEconomyFlight(){
        EconomyFlight economyFlight = new EconomyFlight();

        Passenger normalPassenger = new Passenger(false);

        economyFlight.addPassenger(normalPassenger);
        economyFlight.removePassenger(normalPassenger);
        List<Passenger> passengers = economyFlight.getPassengers();
        assertFalse(passengers.contains(normalPassenger));
    }

    @Test
    public void testRemoveVipPassengerFromEconomyFlight(){
        EconomyFlight economyFlight = new EconomyFlight();

        Passenger vipPassenger = new Passenger(true);

        economyFlight.addPassenger(vipPassenger);
        economyFlight.removePassenger(vipPassenger);
        List<Passenger> passengers = economyFlight.getPassengers();
        assertTrue(passengers.contains(vipPassenger));
    }

    @Test
    public void testRemoveVipPassengerFromBusinessFlight(){
        BusinessFlight businessFlight = new BusinessFlight();

        Passenger vipPassenger = new Passenger(true);

        businessFlight.addPassenger(vipPassenger);
        businessFlight.removePassenger(vipPassenger);
        List<Passenger> passengers = businessFlight.getPassengers();
        assertTrue(passengers.contains(vipPassenger));
    }

}
