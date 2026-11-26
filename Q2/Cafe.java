/*
 * ISB16003 Group Assignment 3
 * Group: L02B02_Group6
 * Members:
 * MUHAMMAD DANISH HO BIN MUHAMMAD DANIAL (5222610009),
 * MUHAMAD ALIF HAKIM BIN HASHIM (5222610019),
 * NUR SYAHDINA BINTI SHAHRUL NIZA (5222610658),
 * NUR IRDINA SYAFIAH BINTI KHAIRUL AFFENDI (5222610200)
 */

/** Validates complete orders before any charge or stock change. */
public class Cafe {
    private final MenuItem[] menu;

    public Cafe(MenuItem[] menu) {
        this.menu=menu;
    }

    /**
     * Three steps: validate every line, charge once, then reduce stock.
     * If validation or payment fails, stock and wallet remain unchanged.
     */
    public double placeOrder(Wallet wallet, String[]lines)
            throws InvalidOrderException, OutOfStockException, InsufficientBalanceException {
        MenuItem[] selected = new MenuItem[lines.length];
        int[] quantities = new int[lines.length];
        double total = 0.0;

        for (int i = 0; i < lines.length; i++) {
            // Required order: parse -> find item -> check quantity -> check stock.
            String[] parts = lines[i].split(":");
            String requestedName = parts[0];
            int qty = Integer.parseInt(parts[1]);

            MenuItem found = null;
            for (MenuItem item : menu) {
                if (item.getName().equals(requestedName)) {
                    found = item;
                    break;
                }
            }
            if (found==null) {
                throw new InvalidOrderException("Unknown item: " + requestedName);
            }
            if (qty<1) {
                throw new InvalidOrderException("Quantity must be at least 1");
            }
            if (qty>found.getStock()) {
                throw new OutOfStockException(
                        "Only "+ found.getStock() + " " + requestedName + " left");
            }

            selected[i] = found;
            quantities[i] = qty;
            total += found.getPrice() * qty;
        }

        wallet.deduct(total);
        for (int i = 0; i < selected.length; i++) {
            selected[i].reduceStock(quantities[i]);
        }
        return total;
    }
}
