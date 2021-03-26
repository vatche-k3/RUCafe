package cafe.models;

import cafe.utils.DonutFlavor;

/**
 * Donut MenuItem
 *
 * @author Reagan McFarland
 */
public class Donut extends MenuItem implements Customizable {

    // Private members
    private DonutFlavor type;
    private DonutFlavor flavor;

    /**
     * Create new instance of Donut. type must be set through setType()
     */
    public Donut() {
        type = null;
        flavor = null;
    }

    /**
     * Set type of Donut
     * @param newType new type of the Donut
     */
    public void setType(DonutFlavor newType) { this.type = newType;}

    /**
     * Add a new DonutFlavors to the Donut
     * @param obj
     * @return
     */
    @Override
    public boolean add(Object obj) {
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        return false;
    }

    @Override
    public double itemPrice() {
        return 0;
    }
}