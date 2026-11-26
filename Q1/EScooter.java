//electric scooter vehicle.

public class EScooter extends Vehicle implements Chargeable {

    private int battery;

    private static final int FREE_MINUTES = 3;
    private static final double RATE_PER_MINUTE = 0.40;
    private static final double MAX_FARE = 10.00;

    public EScooter(String model, int battery) {
        super("ES", model);
        this.battery = battery;
    }

    //calculates scooter fare before tax.
    @Override
    public double calculateFare(int minutes) {

        double fare = 0;

        if (minutes > FREE_MINUTES) {
            fare = (minutes - FREE_MINUTES) * RATE_PER_MINUTE;
        }

        if (fare > MAX_FARE) {
            fare = MAX_FARE;
        }

        return fare;
    }

    @Override
    public int getBattery() {
        return battery;
    }

    //recharges the battery to 100%.
    @Override
    public void recharge() {
        battery = 100;
    }
}
