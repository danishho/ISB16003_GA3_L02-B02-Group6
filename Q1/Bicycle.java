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
 *Represents a bicycle that inherits from Vehicle.
 * Charges RM 2 for every 30 minutes or part thereof.
 */

public final class Bicycle extends Vehicle {

    private static final int BLOCK_MINUTES = 30;
    private static final double RATE_PER_BLOCK = 2.00;

    public Bicycle(String model) {
        super("BK", model);
    }

/*
 * Calculates bicycle fare before tax for complete 30-minute blocks.
 * Adds one block's charge if any minutes remain.
 */
    @Override
    public double calculateFare(int minutes) {

        double fare = (minutes / BLOCK_MINUTES) * RATE_PER_BLOCK;

        if (minutes % BLOCK_MINUTES > 0) {
            fare = fare + RATE_PER_BLOCK;
        }

        return fare;
    }
}
