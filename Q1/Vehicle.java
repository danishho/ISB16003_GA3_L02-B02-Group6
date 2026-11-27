/*ISB16003 Group Assignment 3
 * 
 * 
 * 
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
