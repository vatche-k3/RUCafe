package cafe.models;

import cafe.utils.Constants;
import java.util.ArrayList;

/**
 * Coffee MenuItem
 *
 * @author Reagan McFarland
 */
public class Coffee extends MenuItem implements Customizable {

    // Private members
    private final ArrayList<Constants.COFFEE_ADDINS> addins;
    private Constants.COFFEE_SIZES size;

    /**
     * Create new instance of Coffee. size must be set through setSize()
     */
    public Coffee() {
        // Instantiate members
        addins = new ArrayList<Constants.COFFEE_ADDINS>();
        size = null;
    }

    /**
     * Set size of Coffee
     * @param newSize new size of the Coffee
     */
    public void setSize(Constants.COFFEE_SIZES newSize) {
        this.size = newSize;
    }

    /**
     * Add a new Constants.COFFEE_ADDINS to the Coffee.
     * Note: Attempts to cast object to an Constants.COFFEE_ADDINS type, so you don't have to do type checking beforehand
     * @param obj Addin we are adding to our coffee.
     * @return Whether or not obj was a valid COFFEE_ADDIN and it was successfully appended to the list
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof Constants.COFFEE_ADDINS) {
            Constants.COFFEE_ADDINS newAddinIn = (Constants.COFFEE_ADDINS) obj;
            // Make sure addin is not already in the list
            boolean addinAlreadyPresent = false;
            for(Constants.COFFEE_ADDINS addin : this.addins) {
                if(addin == newAddinIn) {
                    addinAlreadyPresent = true;
                    break;
                }
            }
            if(addinAlreadyPresent) {
                return false;
            } else {
                this.addins.add(newAddinIn);
                return true;
            }
        } else {
            //DEBUG
            System.out.println("REMOVEME: Coffee:39 NOT CASTABLE");
            return false;
        }
    }

    /**
     * Remove a Constants.COFFEE_ADDINS from the Coffee
     * Note: Attempts to cast object to an Constants.COFFEE_ADDINS type, so you don't have to do type checking beforehand
     * @param obj Addin we are removing from our coffee.
     * @return Whether or not obj was a valid COFFEE_ADDIN and it was successfully present and removed from the list
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Constants.COFFEE_ADDINS) {
            Constants.COFFEE_ADDINS newAddinIn = (Constants.COFFEE_ADDINS) obj;
            // Collections.remove returns true if the list was changed, i.e., it was actually in the list
            return this.addins.remove(newAddinIn);
        } else {
            //DEBUG
            System.out.println("REMOVEME: Coffee:39 NOT CASTABLE");
            return false;
        }
    }

    /**
     * Calculate the price of the coffee based on the size and add-ins.
     * @return price of the coffee
     * @throws IllegalStateException coffee size must be set beforehand
     */
    @Override
    public double itemPrice() throws IllegalStateException {
        // Throw an exception if setSize() has not been called
        if(this.size == null) throw new IllegalStateException("Coffee Size has not been set via setSize()");

        // Calculate total addin cost
        // Note: each addin has the same price
        double totalAddinCost = this.addins.size() * Constants.COFFEE_ADDIN_COST;

        // Calculate cost of size
        double sizeCost = this.size.getCost();

        // Return total cost
        return sizeCost + totalAddinCost;
    }
}
