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
 * interface for vehicles that can be recharged
 */

public interface Chargeable {
	
	int LOW_BATTERY = 20;
	int getBattery ();
	void recharge ();

}
