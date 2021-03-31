package cafe.models;

import java.util.ArrayList;

/**
 * An order for the store. This class follows the singleton design pattern as multiple controllers need to be able to update the same instance consistently.
 * Once the order is finalized, the singleton is re-instantiated. This abstraction proves to be very effective in our particular use case.
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class Order implements Customizable {

    // The items in the current order
    ArrayList<MenuItem> itemsInOrder;

    // Singleton setup
    private static Order instance;

    /**
     * The only constructor, the private no argument constructor, can only be called from this class (through getInstance).
     */
    private Order() {
        itemsInOrder = new ArrayList<>();
    }

    /**
     * Retrieve the current order instance. If one does not exist already, it is created.
     * @return Current Order shared instance
     */
    public static Order getInstance() {
        if(instance == null)
            instance = new Order();
        return instance;
    }

    /**
     * Finalize the given order and send it to StoreOrders.
     * This also sets the instance to null, causing the singleton to create a new instance next time this class is called from outside
     */
    public void finalizeOrder() {
        // Add to store orders and reset instance
        StoreOrders.getInstance().add(this);
        instance = null;
    }

    /**
     * Add a MenuItem to the order.
     * @param obj MenuItem we are wanting to add to the order
     * @return status on whether or not the item was successfully added to the order
     */
    @Override
    public boolean add(Object obj) {
        // Make sure the obj is actually a menu item
        if(obj instanceof MenuItem) {
            // Cast and remove
            MenuItem objAsMenuItem = (MenuItem)obj;
            return this.itemsInOrder.add(objAsMenuItem);
        }
        return false;
    }

    /**
     * Remove a MenuItem from the order
     * @param obj MenuItem we are wanting to remove from the order
     * @return status on whether or not the item was successfully removed from the order
     */
    @Override
    public boolean remove(Object obj) {
        // Make sure the obj is actually a menu item
        if(obj instanceof MenuItem) {
            // Cast and remove
            MenuItem objAsMenuItem = (MenuItem)obj;
            return this.itemsInOrder.remove(objAsMenuItem);
        }
        return false;
    }

}