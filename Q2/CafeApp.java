/*
 * ISB16003 Group Assignment 3
 * Group: L02B02_Group6
 * Members: 
 * MUHAMMAD DANISH HO BIN MUHAMMAD DANIAL 5222610009,
 * MUHAMAD ALIF HAKIM BIN HASHIM 5222610019,
 * NUR SYAHDINA BINTI SHAHRUL NIZA 5222610658,
 * NUR IRDINA SYAFIAH BINTI KHAIRUL AFFENDI 5222610200
 */

/** Runs eight orders and prints each outcome, even if an exception occurs. */
public class CafeApp {
    /*
     * Design note-checked vs unchecked exceptions:
     * InvalidOrderException, OutOfStockException, InsufficientBalanceException:
     * CHECKED, because they extend Exception, so Java requires catch or throws.
     * NumberFormatException: UNCHECKED, because it extends RuntimeException.
     * ArrayIndexOutOfBoundsException: UNCHECKED, because it extends RuntimeException.
     * NullPointerException: UNCHECKED, because it extends RuntimeException.
     */
    public static void main(String[] args) {
        MenuItem nasiLemak = new MenuItem("NasiLemak", 6.50, 5);
        MenuItem tehTarik = new MenuItem("TehTarik", 2.50, 10);
        MenuItem rotiCanai = new MenuItem("RotiCanai", 3.00, 2);
        MenuItem[] menu = {nasiLemak,tehTarik,rotiCanai};
        Cafe cafe = new Cafe(menu);
        Wallet wallet = new Wallet(30.00);

        String[][] orders = {
            {"NasiLemak:2","TehTarik:2"},
            {"RotiCanai:3"},
            {"TehTarik:two"},
            {"NasiLemak"},
            {"Laksa:1"},
            {"NasiLemak:3","RotiCanai:1"},
            null,
            {"RotiCanai:2"}
        };

        for (int i = 0; i < orders.length; i++) {
            String status = "";
            try {
                double total = cafe.placeOrder(wallet, orders[i]);
                status = String.format("PAID RM%.2f", total);
            } catch (InvalidOrderException e) {
                status = "INVALID " + e.getMessage();
            } catch (OutOfStockException e) {
                status = "STOCK " + e.getMessage();
            } catch (InsufficientBalanceException e) {
                status = "FUNDS " + e.getMessage();
            } catch (NumberFormatException e) {
                status = "BAD QUANTITY";
            } catch (ArrayIndexOutOfBoundsException e) {
                status = "BAD FORMAT";
            } catch (Exception e) {
                status = "ERROR Unexpected failure";
            } finally {
                System.out.printf("#%d %s | balance RM%.2f%n",
                        i + 1, status, wallet.getBalance());
            }
        }
        System.out.printf("Stock: NasiLemak=%d TehTarik=%d RotiCanai=%d%n",
                nasiLemak.getStock(),tehTarik.getStock(),rotiCanai.getStock());
    }
}
