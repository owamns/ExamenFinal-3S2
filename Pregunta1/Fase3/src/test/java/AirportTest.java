import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class AirportTest {

    @Nested
    public class EconomyFlightTests{
        EconomyFlight economyFlight ;


        @Test
        public void testAddPassengers(){
            economyFlight = new EconomyFlight();

            Passenger normalPassenger = new Passenger(false);
            Passenger vipPassenger = new Passenger(true);

            economyFlight.addPassenger(normalPassenger);
            economyFlight.addPassenger(vipPassenger);

            assertTrue(economyFlight.getPassengers().contains(normalPassenger));
            assertTrue(economyFlight.getPassengers().contains(vipPassenger));
        }

        @Test
        public void testRemoveNormalPassenger(){
            economyFlight = new EconomyFlight();

            Passenger normalPassenger = new Passenger(false);

            economyFlight.addPassenger(normalPassenger);
            economyFlight.removePassenger(normalPassenger);

            assertFalse(economyFlight.getPassengers().contains(normalPassenger));

        }

        @Test
        public void testRemoveVipPassenger(){
            economyFlight = new EconomyFlight();

            Passenger normalPassenger = new Passenger(true);

            economyFlight.addPassenger(normalPassenger);
            economyFlight.removePassenger(normalPassenger);

            assertTrue(economyFlight.getPassengers().contains(normalPassenger));
        }

    }

    @Nested
    public class BusinessFlightTests{

        BusinessFlight businessFlight;

        @Test
        public void testAddPassengers(){
            businessFlight = new BusinessFlight();

            Passenger normalPassenger = new Passenger(false);
            Passenger vipPassenger = new Passenger(true);

            businessFlight.addPassenger(normalPassenger);
            businessFlight.addPassenger(vipPassenger);

            assertFalse(businessFlight.getPassengers().contains(normalPassenger));
            assertTrue(businessFlight.getPassengers().contains(vipPassenger));
        }

        @Test
        public void testRemoveVipPassenger(){
            businessFlight = new BusinessFlight();

            Passenger vipPassenger = new Passenger(true);

            businessFlight.addPassenger(vipPassenger);
            businessFlight.removePassenger(vipPassenger);

            assertTrue(businessFlight.getPassengers().contains(vipPassenger));
        }
    }

    @Nested
    public class PremiumFlightTests{

        PremiumFlight premiumFlight;

        @Test
        public void testAddPassengers(){
            premiumFlight = new PremiumFlight();
            Passenger normalPassenger = new Passenger(false);
            Passenger vipPassenger = new Passenger(true);

            premiumFlight.addPassenger(normalPassenger);
            premiumFlight.addPassenger(vipPassenger);

            assertFalse(premiumFlight.getPassengers().contains(normalPassenger));
            assertTrue(premiumFlight.getPassengers().contains(vipPassenger));
        }

        @Test
        public void testRemoveVipPassenger(){
            premiumFlight = new PremiumFlight();

            Passenger vipPassenger = new Passenger(true);
            premiumFlight.addPassenger(vipPassenger);
            premiumFlight.removePassenger(vipPassenger);

            assertFalse(premiumFlight.getPassengers().contains(vipPassenger));

        }
    }


}
