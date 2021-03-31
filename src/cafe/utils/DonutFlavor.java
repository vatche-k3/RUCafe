package cafe.utils;

/**
 * Enum for Donut flavors
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public enum DonutFlavor {
    GLAZE("Glaze"),
    CHOCOLATE_GLAZE("Chocolate Glaze"),
    CHOCOLATE_FROSTING("Chocolate Frosting"),
    PINK_FROSTING("Pink Frosting"),
    MAPLE("Maple");

    // String representation of the flavor name
    private String flavorString;

    DonutFlavor(String flavorString) {
        this.flavorString = flavorString;
    }

    /**
     * Get String representation of the flavor
     * @return String representation of the flavor
     */
    @Override
    public String toString() {
        return this.flavorString;
    }
}