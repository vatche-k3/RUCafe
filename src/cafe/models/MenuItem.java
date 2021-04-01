package cafe.models;

/**
 * Menu Item Abstract Class
 *
 * @author Regan McFarland, Vatche Kafafian
 */
public abstract class MenuItem {

    /**
     * Abstract function definition to compute the itemPrice of a MenuItem.
     * Sub classes are required to implement this function
     * @return item price of the MenuItem
     */
    public abstract double itemPrice();
}