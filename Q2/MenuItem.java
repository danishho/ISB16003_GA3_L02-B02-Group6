/*
 * ISB16003 Group Assignment 3
 * Group: L02B02_Group6
 * Members: 
 * MUHAMMAD DANISH HO BIN MUHAMMAD DANIAL 5222610009,
 * MUHAMAD ALIF HAKIM BIN HASHIM 5222610019,
 * NUR SYAHDINA BINTI SHAHRUL NIZA 5222610658,
 * NUR IRDINA SYAFIAH BINTI KHAIRUL AFFENDI 5222610200
 */

/** One menu product with a fixed price and mutable stock. */
public class MenuItem {
    private final String name;
    private final double price;
    private int stock;

    public MenuItem(String name, double price, int stock) {
        this.name=name;
        this.price=price;
        this.stock=stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    /** Called only after the full order has been validated and paid for. */
    public void reduceStock(int qty) {
        stock -= qty;
    }
}
