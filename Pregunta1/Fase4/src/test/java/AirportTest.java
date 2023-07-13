import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    @DisplayName("Vuelo Economico")
    @Nested
    public class EconomyFlightTests{
        EconomyFlight economyFlight ;
        Passenger normalPassenger;
        Passenger vipPassenger;

        @BeforeEach
        void setUp(){
            economyFlight = new EconomyFlight("1");
            normalPassenger = new Passenger("Luis", false);
            vipPassenger = new Passenger("Ana", true);
        }

        @Nested
        @DisplayName("Cuando hay un pasajero Regular")
        public class RegularPassenger{

            @Test
            @DisplayName("Luego puedo agregarlo y eliminarlo del vuelo")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verifica todas las condiciones para un pasajero regular en el vuelo",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(normalPassenger)),
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals("Luis", economyFlight.getPassengers().get(0).getName()),
                        () -> assertEquals(true, economyFlight.removePassenger(normalPassenger)),
                        () -> assertEquals(0, economyFlight.getPassengers().size())
                        );
            }
        }

        @Nested
        @DisplayName("Cuando hay un pasajero VIP")
        public class VipPassenger{

            @Test
            @DisplayName("Luego puedo agregarlo pero no eliminarlo del vuelo")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verifica todas las condiciones para un pasajero VIP en el vuelo",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(vipPassenger)),
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals("Ana", economyFlight.getPassengers().get(0).getName()),
                        () -> assertEquals(false, economyFlight.removePassenger(vipPassenger)),
                        () -> assertEquals(1, economyFlight.getPassengers().size())
                );
            }
        }
    }

    @DisplayName("Vuelo de Negocios")
    @Nested
    public class BusinessFlightTests{
        BusinessFlight businessFlight ;
        Passenger normalPassenger;
        Passenger vipPassenger;

        @BeforeEach
        void setUp(){
            businessFlight = new BusinessFlight("2");
            normalPassenger = new Passenger("Luis", false);
            vipPassenger = new Passenger("Ana", true);
        }

        @Nested
        @DisplayName("Cuando hay un pasajero Regular")
        public class RegularPassenger{

            @Test
            @DisplayName("Luego puedo agregarlo y eliminarlo del vuelo")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verifica todas las condiciones para un pasajero regular en el vuelo",
                        () -> assertEquals("2", businessFlight.getId()),
                        () -> assertEquals(false, businessFlight.addPassenger(normalPassenger)),
                        () -> assertEquals(0, businessFlight.getPassengers().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(normalPassenger)),
                        () -> assertEquals(0, businessFlight.getPassengers().size())
                );
            }
        }

        @Nested
        @DisplayName("Cuando hay un pasajero VIP")
        public class VipPassenger{

            @Test
            @DisplayName("Luego puedo agregarlo pero no eliminarlo del vuelo")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verifica todas las condiciones para un pasajero VIP en el vuelo",
                        () -> assertEquals("2", businessFlight.getId()),
                        () -> assertEquals(true, businessFlight.addPassenger(vipPassenger)),
                        () -> assertEquals(1, businessFlight.getPassengers().size()),
                        () -> assertEquals("Ana", businessFlight.getPassengers().get(0).getName()),
                        () -> assertEquals(false, businessFlight.removePassenger(vipPassenger)),
                        () -> assertEquals(1, businessFlight.getPassengers().size())
                );
            }
        }
    }

    @DisplayName("Vuelo Premium")
    @Nested
    public class PremiumFlightTests{
        PremiumFlight premiumFlight ;
        Passenger normalPassenger;
        Passenger vipPassenger;

        @BeforeEach
        void setUp(){
            premiumFlight = new PremiumFlight("3");
            normalPassenger = new Passenger("Luis", false);
            vipPassenger = new Passenger("Ana", true);
        }

        @Nested
        @DisplayName("Cuando hay un pasajero Regular")
        public class RegularPassenger{

            @Test
            @DisplayName("Luego puedo agregarlo y eliminarlo del vuelo")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verifica todas las condiciones para un pasajero regular en el vuelo",
                        () -> assertEquals("3", premiumFlight.getId()),
                        () -> assertEquals(false, premiumFlight.addPassenger(normalPassenger)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(normalPassenger)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size())
                );
            }
        }

        @Nested
        @DisplayName("Cuando hay un pasajero VIP")
        public class VipPassenger{

            @Test
            @DisplayName("Luego puedo agregarlo y eliminarlo del vuelo")
            public void testEconomyFlightRegularPassenger() {
                assertAll("Verifica todas las condiciones para un pasajero VIP en el vuelo",
                        () -> assertEquals("3", premiumFlight.getId()),
                        () -> assertEquals(true, premiumFlight.addPassenger(vipPassenger)),
                        () -> assertEquals(1, premiumFlight.getPassengers().size()),
                        () -> assertEquals("Ana", premiumFlight.getPassengers().get(0).getName()),
                        () -> assertEquals(true, premiumFlight.removePassenger(vipPassenger)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size())
                );
            }
        }
    }
}
