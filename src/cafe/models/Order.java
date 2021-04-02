package cafe.models;

import cafe.utils.Constants;

import java.util.ArrayList;

/**
 * An order for the store. This class follows the singleton design pattern as multiple controllers need to be able to update the same instance consistently.
 * Once the order is finalized, the singleton is re-instantiated. This abstraction proves to be very effective in our particular use case.
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class Order implements Customizable {

    // Tracks the next order id
    public static int NEXT_ORDER_ID = 0;

    // Id of the order
    private int id;
    // The items in the current order
    private final ArrayList<MenuItem> itemsInOrder;
    // If this value is set, the order has been finalized
    private double finalPrice;

    // Singleton setup
    private static Order instance;

    /**
     * The only constructor, the private no argument constructor, can only be called from this class (through getInstance).
     */
    private Order() {
        itemsInOrder = new ArrayList<>();
        finalPrice = 0;
        id = Order.NEXT_ORDER_ID++;
    }

    /**
     * Override the ID of an order. Refer to StoreOrders.resetOrderIds() for the reasoning behind why we need this
     * @param newId newId for the order
     */
    public void setId(int newId) {
        this.id = newId;
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
     * @param finalPrice the final price (including tax) of the order
     * This also sets the instance to null, causing the singleton to create a new instance next time this class is called from outside
     */
    public void finalizeOrder(double finalPrice) {
        // Add to store orders and reset instance
        this.finalPrice = finalPrice;
        StoreOrders.getInstance().add(this);
        instance = null;
    }

    /**
     * Get items in order
     * @return items in order
     */
    public ArrayList<MenuItem> getItemsInOrder() {
        return this.itemsInOrder;
    }

    /**
     * Get the final price of an order. This value is only set through finalizeOrder
     * @return final price of order
     */
    public double getFinalPrice() {
        return this.finalPrice;
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

    /**
     * Return a string represntation of the order.
     * Follows the format "(id) - Total Price = (finalPrice)"
     * @return String representation following the format above
     */
    @Override
    public String toString() {
        String ret = "";
        // Add prefix and id
        ret += this.id + Constants.ORDER_STRING_DELIMITER;
        // Add total price
        ret += Constants.ORDER_TOTAL_PRICE_PREFIX_STR + String.format(Constants.CURRENCY_FORMAT_STRING, this.finalPrice);

        return ret;
    }


}