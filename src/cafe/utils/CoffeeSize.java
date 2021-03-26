package cafe.utils;

/**
 * Enum for Coffee sizes
 */
public enum CoffeeSize {
    SHORT(Constants.COFFEE_SIZE_SHORT_PRICE),
    TALL(Constants.COFFEE_SIZE_TALL_PRICE),
    GRANDE(Constants.COFFEE_SIZE_GRANDE_PRICE),
    VENTI(Constants.COFFEE_SIZE_VENTI_PRICE);

    private final double cost;
    CoffeeSize(double cost) {
        this.cost = cost;
    }

    /**
     * Get base cost of a coffee by its size
     * @return base cost of a coffee by its size
     */
    public double getCost() {
        return this.cost;
    }
}