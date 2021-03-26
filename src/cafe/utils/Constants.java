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
    public static final double DONUT_TYPE_YEAST_PRICE = 1.39;
    public static final double DONUT_TYPE_CAKE_PRICE = 1.59;
    public static final double DONUT_TYPE_HOLE_PRICE = 0.33;
    public static final String DONUT_STRING_DELIMITER = ":";

    // Coffee specific constants
    public static final double COFFEE_ADDIN_COST = 0.2;
    public static final double COFFEE_BASE_PRICE = 1.99;
    public static final double COFFEE_SIZE_SHORT_PRICE = COFFEE_BASE_PRICE;
    public static final double COFFEE_SIZE_TALL_PRICE = COFFEE_BASE_PRICE + 0.5;
    public static final double COFFEE_SIZE_GRANDE_PRICE = COFFEE_BASE_PRICE + 1.0;
    public static final double COFFEE_SIZE_VENTI_PRICE = COFFEE_BASE_PRICE + 1.5;
}
