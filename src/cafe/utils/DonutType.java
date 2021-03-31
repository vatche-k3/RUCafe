package cafe.utils;

/**
 * Enum for Donut types
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public enum DonutType {
    YEAST(Constants.DONUT_TYPE_YEAST_PRICE, "Yeast"),
    CAKE(Constants.DONUT_TYPE_CAKE_PRICE, "Cake"),
    HOLE(Constants.DONUT_TYPE_HOLE_PRICE, "Hole");

    private final double cost;
    // name of the type as a String
    private final String typeString;

    DonutType(double cost, String typeString) {
        this.cost = cost;
        this.typeString = typeString;
    }

    /**
     * Get the base cost of a donut by its type
     * @return base cost of a dunut by its type
     */
    public double getCost() { return this.cost; }

    /**
     * Get String representation of the type
     * @return String representation of the type
     */
    @Override
    public String toString() {
        return this.typeString;
    }
}