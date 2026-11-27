/*ISB16003 Group Assignment 3
 * 
 * 
 * 
 * 
 * interface for vehicles that can be recharged
 */

public interface Chargeable {
	
	int LOW_BATTERY = 20;
	int getBattery ();
	void recharge ();

}
