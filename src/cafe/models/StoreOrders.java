package cafe.models;

import java.util.ArrayList;

public class StoreOrders implements Customizable {

    ArrayList<Order> orders;

    private static StoreOrders instance = null;
    private StoreOrders() {
        this.orders = new ArrayList<>();
    }
    public static StoreOrders getInstance() {
        if(instance == null)
            instance = new StoreOrders();
        return instance;
    }

    /**
     * Export all orders to a file
     */
    public void exportOrdersToFile() {
        //TODO
        // Open the file dialog, and write orders
    }

    public void test() {
        for(Order order : this.orders) {
            System.out.println("new order");
            for(MenuItem mItem : order.itemsInOrder) {
                System.out.println("\t" + mItem.itemPrice());
            }
        }
    }


    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order) {
            Order objAsOrder = (Order)obj;
            this.orders.add(objAsOrder);
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        return false;
    }
}
