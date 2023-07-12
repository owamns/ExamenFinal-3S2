public class Airport {
    public static void main(String[] args) {

        Flight economyFlight = new Flight("economy");
        Flight businessbusFlight = new Flight("business");

        //Normal Passenger
        Passenger nPassenger = new Passenger(false);

        //VIP Passenger
        Passenger vPassenger = new Passenger(true);

        System.out.println(economyFlight.addPassenger(nPassenger)); //true
        System.out.println(economyFlight.addPassenger(vPassenger)); //true
        System.out.println(economyFlight.removePassenger(nPassenger)); //true
        System.out.println(economyFlight.removePassenger(vPassenger)); //false

        System.out.println(businessbusFlight.addPassenger(nPassenger)); //false
        System.out.println(businessbusFlight.addPassenger(vPassenger)); //true
        System.out.println(businessbusFlight.removePassenger(vPassenger)); //false
    }
}
