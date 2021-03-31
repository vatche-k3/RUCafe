package cafe.models;

import cafe.utils.Constants;
import cafe.utils.DonutFlavor;
import cafe.utils.DonutType;

/**
 * Donut MenuItem
 *
 * @author Reagan McFarland
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
     * Get the type of the donut
     * @return type of the donut
     */
    public DonutType getType() {
        return this.type;
    }

    /**
     * Get the flavor of the donut
     * @return flavor of the donut
     */
    public DonutFlavor getFlavor() {
        return this.flavor;
    }

    /**
     * Get the quantity of donuts we are ordering
     * @return quantity we are ordering of the donut
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Set the quantity of donuts in the order line item
     * @param newQuantity the new quantity of donuts for the line item
     */
    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    /**
     * Compare 2 donuts against eachother based on type and flavor
     * @param obj Donut to compare against
     * @return whether or not the donuts have the same type and flavor
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Donut) {
            Donut objAsDonut = (Donut)obj;
            return objAsDonut.getType() == this.getType() && objAsDonut.getFlavor() == this.getFlavor();
        }
        return false;
    }

    /**
     * Get a string representation of the donut order.
     * Follows the format
     *      "DonutType:DonutFlavor:(quantity)"
     * @return string representation following the format above
     */
    @Override
    public String toString() {
        String ret = "";

        // Append donut type and flavor
        ret += this.getType() + Constants.DONUT_STRING_DELIMITER + this.getFlavor();

        // Append quantity
        ret += Constants.DONUT_STRING_DELIMITER + "(" + this.quantity + ")";

        return ret;
    }

    /**
     * Calculate the price of the donut based on the type, flavor, and quantity
     * @return price of the donut
     */
    @Override
    public double itemPrice() {
        // Calculate base cost (type)
        double baseCost = this.getType().getCost();
        // Multiply by quantity and return
        return baseCost * this.getQuantity();
    }
}