package cafe.utils;

import cafe.models.StoreOrders;

/**
 * Global Constants to be used throughout the Project
 * @author Reagan McFarland, Vatche Kafafian
 */
public class Constants {
    // Window titles
    public static final String MAIN_MENU_WINDOW_TITLE = "Main Menu";
    public static final String CURRENT_ORDER_DETAILS_WINDOW_TITLE = "Current Order Details";
    public static final String ORDERING_COFFEE_WINDOW_TITLE = "New Cofee Order";
    public static final String ORDERING_DONUTS_WINDOW_TITLE = "New Donut Order";
    public static final String ORDERS_PAGE_WINDOW_TITLE = "Past Orders";

    // Donut specific constants
    private static final double DONUT_TYPE_YEAST_PRICE = 1.39;
    private static final double DONUT_TYPE_CAKE_PRICE = 1.59;
    private static final double DONUT_TYPE_HOLE_PRICE = 0.33;

    // Coffee specific constants
    public static final double COFFEE_ADDIN_COST = 0.2;
    private static final double COFFEE_BASE_PRICE = 1.99;
    private static final double COFFEE_SIZE_SHORT_PRICE = COFFEE_BASE_PRICE;
    private static final double COFFEE_SIZE_TALL_PRICE = COFFEE_BASE_PRICE + 0.5;
    private static final double COFFEE_SIZE_GRANDE_PRICE = COFFEE_BASE_PRICE + 1.0;
    private static final double COFFEE_SIZE_VENTI_PRICE = COFFEE_BASE_PRICE + 1.5;

    /**
     * Enum for Donut types
     */
    public enum DONUT_TYPES {
        YEAST(DONUT_TYPE_YEAST_PRICE),
        CAKE(DONUT_TYPE_CAKE_PRICE),
        HOLE(DONUT_TYPE_HOLE_PRICE);

        private final double cost;
        DONUT_TYPES(double cost) { this.cost = cost; }

        /**
         * Get the base cost of a donut by its type
         * @return base cost of a dunut by its type
         */
        public double getCost() { return this.cost; }
    }

    /**
     * Enum for Donut flavors
     */
    public enum DONUT_FLAVORS {
        GLAZE,
        CHOCOLATE_GLAZE,
        CHOCOLATE_FROSTING,
        PINK_FROSTING,
        MAPLE
    }

    /**
     * Enum for Coffee sizes
     */
    public enum COFFEE_SIZES {
        SHORT(COFFEE_SIZE_SHORT_PRICE),
        TALL(COFFEE_SIZE_TALL_PRICE),
        GRANDE(COFFEE_SIZE_GRANDE_PRICE),
        VENTI(COFFEE_SIZE_VENTI_PRICE);

        private final double cost;
        COFFEE_SIZES(double cost) {
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

    /**
     * Enum for Coffee "Add-ins"
     */
    public enum COFFEE_ADDINS {
        CREAM,
        SYRUP,
        MILK,
        CARAMEL,
        WHIPPED_CREAM;
    }
}
