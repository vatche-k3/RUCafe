package cafe.models;

/**
 * Customizable interface. Provides add() and remove() for any object that is customizable, such as:
 * Coffee, Order, StoreOrder, etc.
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public interface Customizable {
    /**
     * Add an object
     * @param obj object to be added
     * @return status of the operation
     */
    boolean add(Object obj);

    /**
     * Remove an object
     * @param obj object to be removed
     * @return status of the operation
     */
    boolean remove(Object obj);
}