package cafe.models;

import cafe.utils.CoffeeAddin;
import cafe.utils.CoffeeSize;
import cafe.utils.Constants;

import java.util.ArrayList;

/**
 * Coffee MenuItem
 *
 * @author Reagan McFarland
 */
public class Coffee extends MenuItem implements Customizable {

    // Private members
    private final ArrayList<CoffeeAddin> addins;
    private CoffeeSize size;
    private int quantity;

    /**
     * Create new instance of Coffee
     */
    public Coffee() {
        // Instantiate members
        addins = new ArrayList<>();
        size = null;
        quantity = 1;
    }

    /**
     * Get the quantity of coffees we are ordering
     *
     * @return quantity we are ordering of the coffee
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Set the quantity of coffee in the order line item
     *
     * @param newQuantity the new quantity of coffees for the line item
     */
    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    /**
     * Set size of Coffee
     *
     * @param newSize new size of the Coffee
     */
    public void setSize(CoffeeSize newSize) {
        this.size = newSize;
    }

    /**
     * Add a new Constants.COFFEE_ADDINS to the Coffee.
     * Note: Attempts to cast object to an Constants.COFFEE_ADDINS type, so you don't have to do type checking beforehand
     *
     * @param obj Addin we are adding to our coffee.
     * @return Whether or not obj was a valid COFFEE_ADDIN and it was successfully appended to the list.
     * Note that it will not add an addin if there is Constants.MAX_UNIQUE_ADDIN already present for the addin in the addin list
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof CoffeeAddin) {
            CoffeeAddin newAddinIn = (CoffeeAddin) obj;

            // Make sure we aren't adding more than MAX_UNIQUE_ADDIN to the addin list
            int currentAddinCount = 0;
            for (CoffeeAddin addin : this.addins) {
                if (addin == newAddinIn) {
                    currentAddinCount++;
                }
            }

            // If we are already at max, return false
            if (currentAddinCount == Constants.MAX_UNIQUE_ADDIN_COUNT) {
                return false;
            } else {
                return this.addins.add(newAddinIn);
            }
        } else {
            return false;
        }
    }

    /**
     * Remove a Constants.COFFEE_ADDINS from the Coffee
     * Note: Attempts to cast object to an Constants.COFFEE_ADDINS type, so you don't have to do type checking beforehand
     *
     * @param obj Addin we are removing from our coffee.
     * @return Whether or not obj was a valid COFFEE_ADDIN and it was successfully present and removed from the list
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof CoffeeAddin) {
            CoffeeAddin newAddinIn = (CoffeeAddin) obj;
            // Collections.remove returns true if the list was changed, i.e., it was actually in the list
            return this.addins.remove(newAddinIn);
        } else {
            return false;
        }
    }

    /**
     * Calculate the price of the coffee based on the size and add-ins.
     *
     * @return price of the coffee
     * @throws IllegalStateException coffee size must be set beforehand
     */
    @Override
    public double itemPrice() throws IllegalStateException {
        // Throw an exception if setSize() has not been called
        if (this.size == null) throw new IllegalStateException("Coffee Size has not been set via setSize()");

        // Calculate total addin cost
        // Note: each addin has the same price
        double totalAddinCost = this.addins.size() * Constants.COFFEE_ADDIN_COST;

        // Calculate cost of size
        double sizeCost = this.size.getCost();

        // Return total cost
        return sizeCost + totalAddinCost;
    }

    /**
     * Get a string representation of the coffee order.
     * Follows the format
     * "Coffee(quantity):CoffeeSize:[CoffeeAddin1, CoffeeAddin2, ...]"
     *
     * @return String representation following the format above
     */
    @Override
    public String toString() {
        String ret = "";
        // Append Coffee(quantity)
        ret += "Coffee(" + this.quantity + ")" + Constants.COFFEE_STRING_DELIMITER;
        // Append CoffeeSize
        ret += this.size + Constants.COFFEE_STRING_DELIMITER;
        // Append addins
        ret += "[" + this.addins.toString() + "]";
        return ret;
    }
}