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

    /**
     * Get orders list
     * @return orders list
     */
    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    /**
     * Add an Order to the orders list
     * @param obj Order to be added
     * @return status of the operation
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order) {
            Order objAsOrder = (Order)obj;
            this.orders.add(objAsOrder);
        }
        return false;
    }

    /**
     * Remove an Order from the orders list
     * @param obj object to be removed
     * @return status of the operation
     */
    @Override
    public boolean remove(Object obj) {
       if(obj instanceof Order) {
           Order objAsOrder = (Order) obj;
           return this.orders.remove(objAsOrder);
       }
       return false;
    }
}