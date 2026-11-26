/*
 * Runs the UniRide fleet pricing and battery recharging.
 * Uses Vehicle and Chargeable references to handle different vehicle type.
 */
public class UniRideApp {

    // Calculates total revenue including tax for the fleet.
    public static double totalRevenue(Vehicle[] fleet, int minutes) {

        double revenue = 0.0;

        for (Vehicle vehicle : fleet) {
            revenue = revenue + vehicle.totalFare(minutes);
        }

        return revenue;
    }

    // Recharges units below LOW_BATTERY and returns how many.
    public static int rechargeLow(Chargeable[] units) {

        int recharged = 0;

        for (Chargeable unit : units) {

            if (unit.getBattery() < Chargeable.LOW_BATTERY) {
                unit.recharge();
                recharged = recharged + 1;
            }
        }

        return recharged;
    }

    // Creates the sample vehicles and prints the results.
    public static void main(String[] input) {

        EScooter s1 = new EScooter("Volt-S1", 85);
        Bicycle b1 = new Bicycle("City Cruiser");
        ElectricShuttle h1 = new ElectricShuttle("Campus Loop", 15);
        EScooter s2 = new EScooter("Volt-S2", 20);

        Vehicle[] fleet = {s1, b1, h1, s2};
        Chargeable[] units = {s1, h1, s2};

        final int RIDE_MINUTES = 25;

        System.out.println("Vehicles created: " + Vehicle.getCount());

        for (Vehicle vehicle : fleet) {
            System.out.printf(
                    "%s: RM%.2f%n",
                    vehicle,
                    vehicle.totalFare(RIDE_MINUTES));
        }

        System.out.printf(
                "Revenue (" + RIDE_MINUTES + "min): RM%.2f%n",
                totalRevenue(fleet, RIDE_MINUTES));

        System.out.printf(
                "Edge cases: RM%.2f | RM%.2f | RM%.2f | RM%.2f%n",
                s1.totalFare(2),
                s1.totalFare(40),
                b1.totalFare(61),
                h1.totalFare(10));

        System.out.println("Recharged: " + rechargeLow(units));

        System.out.print("Battery levels:");

        for (Chargeable unit : units) {
            System.out.print(" " + unit.getBattery());
        }

        System.out.println();

    }
}

