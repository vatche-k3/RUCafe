package cafe.models;

/**
 * Donut MenuItem
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class Donut extends MenuItem implements Customizable {
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