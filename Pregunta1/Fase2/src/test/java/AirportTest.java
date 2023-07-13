import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class AirportTest {

    @Test
    public void testAddPassengersEconomyFlight(){
        EconomyFlight economyFlight = new EconomyFlight("1");

        Passenger normalPassenger = new Passenger("Luis", false);
        Passenger vipPassenger = new Passenger("Ana", true);

        economyFlight.addPassenger(normalPassenger);
        economyFlight.addPassenger(vipPassenger);

        assertEquals("Luis", economyFlight.getPassengers().get(0).getName());
        assertEquals("Ana", economyFlight.getPassengers().get(1).getName());
    }

    @Test
    public void testAddNormalPassengerBusinessFlight(){
        BusinessFlight businessFlight = new BusinessFlight("2");

        Passenger normalPassenger = new Passenger("Luis", false);

        businessFlight.addPassenger(normalPassenger);
        List<Passenger> passengers = businessFlight.getPassengers();
        assertFalse(passengers.contains(normalPassenger));
    }

    @Test
    public void testAddVipPassengerBusinessFlight(){
        BusinessFlight businessFlight = new BusinessFlight("2");

        Passenger vipPassenger = new Passenger("Ana", true);

        businessFlight.addPassenger(vipPassenger);
        List<Passenger> passengers = businessFlight.getPassengers();
        assertEquals("Ana", businessFlight.getPassengers().get(0).getName());
    }

    @Test
    public void testRemoveNormalPassengerFromEconomyFlight(){
        EconomyFlight economyFlight = new EconomyFlight("1");

        Passenger normalPassenger = new Passenger("Luis", false);

        economyFlight.addPassenger(normalPassenger);
        economyFlight.removePassenger(normalPassenger);
        List<Passenger> passengers = economyFlight.getPassengers();
        assertFalse(passengers.contains(normalPassenger));
    }

    @Test
    public void testRemoveVipPassengerFromEconomyFlight(){
        EconomyFlight economyFlight = new EconomyFlight("1");

        Passenger vipPassenger = new Passenger("Ana", true);

        economyFlight.addPassenger(vipPassenger);
        economyFlight.removePassenger(vipPassenger);
        List<Passenger> passengers = economyFlight.getPassengers();
        assertEquals("Ana", passengers.get(0).getName());
    }

    @Test
    public void testRemoveVipPassengerFromBusinessFlight(){
        BusinessFlight businessFlight = new BusinessFlight("2");

        Passenger vipPassenger = new Passenger("Ana", true);

        businessFlight.addPassenger(vipPassenger);
        businessFlight.removePassenger(vipPassenger);
        List<Passenger> passengers = businessFlight.getPassengers();
        assertEquals("Ana", passengers.get(0).getName());
    }

}
