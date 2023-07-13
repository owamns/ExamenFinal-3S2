public class Airport {
    public static void main(String[] args) {

        Flight economyFlight = new Flight("1", "economy");
        Flight businessbusFlight = new Flight("2", "business");

        //Normal Passenger
        Passenger nPassenger = new Passenger("Luis",false);

        //VIP Passenger
        Passenger vPassenger = new Passenger("Ana",true);

        economyFlight.addPassenger(nPassenger); //true
        economyFlight.addPassenger(vPassenger); //true
        economyFlight.removePassenger(nPassenger); //true
        economyFlight.removePassenger(vPassenger); //false

        System.out.println("Lista de pasajeros del vuelo economico");
        for (Passenger passenger: economyFlight.getPassengers()){
            System.out.println(passenger.getName());
        }

        businessbusFlight.addPassenger(nPassenger); //false
        businessbusFlight.addPassenger(vPassenger); //true
        businessbusFlight.removePassenger(vPassenger); //false

        System.out.println("Lista de pasajeros del vuelo de negocios");
        for (Passenger passenger: businessbusFlight.getPassengers()){
            System.out.println(passenger.getName());
        }

    }
}
