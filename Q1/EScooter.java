/*
 * ISB16003 Group Assignment 3
 * Group: L02B02_Group6
 * Members:
 * MUHAMMAD DANISH HO BIN MUHAMMAD DANIAL (5222610009),
 * MUHAMAD ALIF HAKIM BIN HASHIM (5222610019),
 * NUR SYAHDINA BINTI SHAHRUL NIZA (5222610658),
 * NUR IRDINA SYAFIAH BINTI KHAIRUL AFFENDI (5222610200)
 */

/*  
 *electric scooter vehicle.
 */

public class EScooter extends Vehicle implements Chargeable {

    private int battery;

    private static final int FREE_MINUTES = 3;
    private static final double RATE_PER_MINUTE = 0.40;
    private static final double MAX_FARE = 10.00;
    private static final int FULL_BATTERY = 100;

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
        battery = FULL_BATTERY;
    }
}
