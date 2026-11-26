/*
 * ISB16003 Group Assignment 3
 * Group: L02B02_Group6
 * Members:
 * MUHAMMAD DANISH HO BIN MUHAMMAD DANIAL (5222610009),
 * MUHAMAD ALIF HAKIM BIN HASHIM (5222610019),
 * NUR SYAHDINA BINTI SHAHRUL NIZA (5222610658),
 * NUR IRDINA SYAFIAH BINTI KHAIRUL AFFENDI (5222610200)
 */
/** A prepaid e-wallet that refuses charges exceeding its balance. */
public class Wallet {
    private double balance;

    public Wallet(double balance) {
        this.balance=balance;
    }

    public double getBalance() {
        return balance;
    }

    /** Validate funds before changing the balance. */
    public void deduct(double amount) throws InsufficientBalanceException {
        if (balance<amount) {
            throw new InsufficientBalanceException(String.format(
                    "Insufficient balance: need RM%.2f, have RM%.2f", amount, balance));
        }
        balance -= amount;
    }
}
