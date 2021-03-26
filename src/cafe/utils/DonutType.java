package cafe.utils;

/**
 * Enum for Donut types
 */
public enum DonutType {
    YEAST(Constants.DONUT_TYPE_YEAST_PRICE),
    CAKE(Constants.DONUT_TYPE_CAKE_PRICE),
    HOLE(Constants.DONUT_TYPE_HOLE_PRICE);

    private final double cost;
    DonutType(double cost) { this.cost = cost; }

    /**
     * Get the base cost of a donut by its type
     * @return base cost of a dunut by its type
     */
    public double getCost() { return this.cost; }
}
