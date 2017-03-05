package CommunityGames;

import javafx.util.Pair;

import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class WallStreet {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        SortedMap<String, OrderBook> orders = new TreeMap<>();

        Boolean hasTrade = false;
        for (int i = 0; i < N; i++) {
            String symbol = in.next();
            OrderBook book;
            if (!orders.containsKey(symbol)){
                book = new OrderBook(symbol);
                orders.put(symbol, book);
            } else {
                book = orders.get(symbol);
            }
            String verb = in.next();
            int qty = in.nextInt();
            double price = in.nextFloat();
            if (verb.equals("BUY")) {
                hasTrade= book.addBuy(qty, price) || hasTrade;
            } else {
                hasTrade= book.addSell(qty, price) || hasTrade;
            }

        }

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");
        if (!hasTrade){
            System.out.println("NO TRADE");
        }
    }

    static class OrderBook {
        private SortedMap<Double, ArrayList<Integer>> buyOrders = new TreeMap<>();
        private SortedMap<Double, ArrayList<Integer>> sellOrders = new TreeMap<>();
        public ArrayList<Pair<Double, Integer>> executions = new ArrayList<>();
        private final String symbol;

        OrderBook(String symbol) {
            this.symbol = symbol;
        }

        private Boolean updateBook(SortedMap<Double, ArrayList<Integer>> book,
                                   SortedMap<Double, ArrayList<Integer>> bookDest,
                                   int qty, double price, Boolean isBuy) {
            Boolean executed=false;
            // check if it matches a sell order
            while (book.size()>0 && qty>0) {
                double execPrice = isBuy ? book.firstKey() : book.lastKey();
                if (isBuy ? execPrice<=price : execPrice>=price) {
                    ArrayList<Integer> quantities = book.get(execPrice);
                    for(int i=0; i<quantities.size() && qty>0; i++) {
                        int execQty = Math.min(qty, quantities.get(i));
                        executions.add(new Pair<>(execPrice, execQty));
                        qty-=execQty;
                        System.out.format("%s %d %.2f%n", symbol, execQty, execPrice);
                        executed = true;
                        int bookQty = quantities.get(i) - execQty;
                        if (bookQty!=0) {
                            quantities.set(i, bookQty);
                        } else {
                            quantities.remove(i--);
                        }
                    }
                    if (quantities.size() == 0) {
                        book.remove(execPrice);
                    }
                }
                else
                    break;
            }
            if (qty==0)
                // everything is executed
                return executed;
            if (bookDest.containsKey(price)) {
                bookDest.get(price).add(qty);
            } else {
                ArrayList<Integer> quantities = new ArrayList<>();
                quantities.add(qty);
                bookDest.put(price, quantities);
            }
            return executed;
        }

        public Boolean addBuy(int qty, double price) {
            return updateBook(buyOrders, sellOrders, qty, price, true);
        }

        public Boolean addSell(int qty, double price) {
            return updateBook(sellOrders, buyOrders, qty, price, false);
        }

        public Boolean dumpExec() {
            for(Pair<Double, Integer> exec : this.executions) {
                System.err.format("%s %d %.2f%n", symbol, exec.getValue(), exec.getKey());
            }
            return this.executions.size()>0;
        }
    }
}