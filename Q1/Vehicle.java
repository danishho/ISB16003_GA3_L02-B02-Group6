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
 * Vehicle is abstract because different vehicle types have
 * different fare calculations and should not be created directly.
 *
 * Chargeable is an interface because not every vehicle uses a battery.
 * It allows different battery-powered vehicles to share charging behaviour.
 */

//abstract parent class for all vehicles.
public abstract class Vehicle {
	
	private static int count = 0;
	public static final double SERVICE_TAX = 0.06 ;
	
	private final String id;
	private final String model;

	//creates a vehicle with unique ID
	public Vehicle(String prefix, String model) {
		count++;
		this.id = prefix + String.format("%03d", count);
		this.model = model;
	}
	
	// Each vehicle type calculates its own fare.
    public abstract double calculateFare(int minutes);
    
	//calculates fare including service tax
	public final double totalFare(int minutes) {
		return calculateFare(minutes) * (1+ SERVICE_TAX);
	}
	
	public static int getCount() {
		return count;
	} 
	public String getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return id + " " + model;
	}
}
