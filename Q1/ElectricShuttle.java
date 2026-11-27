/*ISB16003 Group Assignment 3
 * 
 * 
 * 
 * 
 * Represents an electric shuttle that inherits from Vehicle.
 * Implements Chargeable to provide battery and recharge behaviours.
 */
public class ElectricShuttle extends Vehicle implements Chargeable {

    private int battery;

    private static final int BASE_MINUTES = 15;
    private static final double BASE_FARE = 5.00;
    private static final double RATE_PER_EXTRA_MINUTE = 0.50;
    private static final int FULL_BATTERY = 100;

    public ElectricShuttle(String model, int battery) {
        super("SH", model);
        this.battery = battery;
    }

/* 
 * Calculates shuttle fare before tax : RM 5 for the first 15 minutes.
 * then RM 0.50 for each additional minute.
 */
    @Override
    public double calculateFare(int minutes) {

        double fare = BASE_FARE;

        if (minutes > BASE_MINUTES) {
            fare = fare + (minutes - BASE_MINUTES) * RATE_PER_EXTRA_MINUTE;
        }

        return fare;
    }

    @Override
    public int getBattery() {
        return battery;
    }

    // Recharges the battery to 100%.
    @Override
    public void recharge() {
        battery =FULL_BATTERY;
    }
}