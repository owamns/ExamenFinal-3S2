public class Passenger {
    private boolean VIP;
    private String name;
    public Passenger(String name, boolean VIP) {
        this.name = name;
        this.VIP = VIP;
    }

    public boolean isVIP() {
        return VIP;
    }

    public String getName() {
        return name;
    }
}
