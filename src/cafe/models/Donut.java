package cafe.models;

import cafe.utils.Constants;
import cafe.utils.DonutFlavor;
import cafe.utils.DonutType;

/**
 * Donut MenuItem
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class Donut extends MenuItem {

    // Private members
    private final DonutType type;
    private final DonutFlavor flavor;
    private int quantity;

    /**
     * Create new instance of Donut.
     */
    public Donut(DonutType type, DonutFlavor flavor, int quantity) {
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
    }


    /**
     * Get the quantity of donuts we are ordering
     *
     * @return quantity we are ordering of the donut
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Set the quantity of donuts in the order line item
     *
     * @param newQuantity the new quantity of donuts for the line item
     */
    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    /**
     * Compare 2 donuts against each other based on type and flavor
     *
     * @param obj Donut to compare against
     * @return whether or not the donuts have the same type and flavor
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Donut) {
            Donut objAsDonut = (Donut) obj;
            return objAsDonut.type == this.type && objAsDonut.flavor == this.flavor;
        }
        return false;
    }

    /**
     * Get a string representation of the donut order.
     * Follows the format
     * "DonutType:DonutFlavor:(quantity)"
     *
     * @return string representation following the format above
     */
    @Override
    public String toString() {
        String ret = "";
        // Append donut type and flavor
        ret += this.type + Constants.DONUT_STRING_DELIMITER + this.flavor;
        // Append quantity
        ret += Constants.DONUT_STRING_DELIMITER + "(" + this.quantity + ")";
        return ret;
    }

    /**
     * Calculate the price of the donut based on the type, flavor, and quantity
     *
     * @return price of the donut
     */
    @Override
    public double itemPrice() {
        // Calculate base cost (type)
        double baseCost = this.type.getCost();
        // Multiply by quantity and return
        return baseCost * this.getQuantity();
    }
}