/*
 * ISB16003 Group Assignment 3
 * Group number: 6
 * Members: 
 * MUHAMMAD DANISH HO BIN MOHD DANIAL 5222610009,
 * MUHAMAD ALIF HAKIM BIN HASHIM 5222610019,
 * NUR SYAHDINA BINTI SHAHRUL NIZA 5222610658,
 * NUR IRDINA SYAFIAH BINTI KHAIRUL AFFENDI 5222610200
 */

/** Checked exception when the wallet cannot cover an order. */
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}