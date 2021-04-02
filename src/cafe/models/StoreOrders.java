package cafe.models;

import java.util.ArrayList;

/**
 * All the orders for the store. This class follows the singleton design pattern as we really want one shared instance
 * to be shared across the project from multiple controllers as easy as possible. This abstraction proves to be very effective in our particular case.
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class StoreOrders implements Customizable {

    // All orders
    ArrayList<Order> orders;

    // Singleton setup
    private static StoreOrders instance = null;

    /**
     * The only constructor, the private no argument constructor, can only be called from this class (through getInstance)
     *
     */
    private StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     * Retrieve the current store orders instance. If one does not exist, it is created.
     * @return Current Store Orders shared instance
     */
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
     * Reset the order Ids of the orders. We need this because when you delete an order, the Ids are no longer
     * mapped to the index correctly, and you end up with ID gaps that don't make much sense for the user.
     */
    public void resetOrderIds() {
        // Reset next order id
        Order.NEXT_ORDER_ID = 0;
        for(Order order : this.orders) {
            order.setId(Order.NEXT_ORDER_ID++);
        }
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