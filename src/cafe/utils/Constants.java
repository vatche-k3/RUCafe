package cafe.utils;

import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Global Constants to be used throughout the Project
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class Constants {
    public static final String CURRENCY_FORMAT_STRING = "$%,.2f";
    public static final char SPACE_CHARACTER = ' ';
    public static final char ENUM_SPACE_REPLACEMENT_CHARACTER = '_';
    public static final double SALES_TAX_RATE = 0.06625;
    public static final String FILE_OUTPUT_MENU_ITEM_PREFIX = "\t - ";

    // Order constants
    public static final String ORDER_TREE_ROOT_NODE_STRING= "Orders";
    public static final String ORDER_STRING_DELIMITER = " - ";
    public static final String ORDER_TOTAL_PRICE_PREFIX_STR = "Total Price = ";

    // Window constants
    public static final String MAIN_MENU_WINDOW_TITLE = "Main Menu";
    public static final String CURRENT_ORDER_DETAILS_WINDOW_TITLE = "Current Order Details";
    public static final String ORDERING_COFFEE_WINDOW_TITLE = "New Coffee Order";
    public static final String ORDERING_DONUTS_WINDOW_TITLE = "New Donut Order";
    public static final String ORDERS_PAGE_WINDOW_TITLE = "Past Orders";
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 500;

    // File chooser constant
    public static final String FILE_CHOOSER_EXPORT_TITLE = "Open Target File for Export";
    public static final ExtensionFilter TEXT_FILES_EXTENSION_FILTER = new ExtensionFilter("Text Files", "*.txt");
    public static final ExtensionFilter ALL_FILES_EXTENSION_FILTER = new ExtensionFilter("All Files", "*.*");

    // Alert messages
    public static final String SUCCESSFULLY_ADDED_ITEM_TO_ORDER_MSG = "Successfully added the item to the order.";
    public static final String SUCCESSFULLY_PLACED_ORDER_MSG = "Successfully added order to store orders.";
    public static final String CANCELLED_ORDER_MSG = "Successfully cancelled order.";
    public static final String NO_FILE_SELECTED_MSG = "No file selected. Please try again.";
    public static final String FAILED_TO_CREATE_FILE_MSG = "Failed to create new file. Please try again.";
    public static final String FAILED_TO_WRITE_FILE_MSG = "Failed to write to the file. Please try again";
    public static final String SUCCESSFULLY_EXPORTED_STORE_ORDERS_MSG = "Successfully exported store orders to file.";

    // Donut specific constants
    public static final double DONUT_TYPE_YEAST_PRICE = 1.39;
    public static final double DONUT_TYPE_CAKE_PRICE = 1.59;
    public static final double DONUT_TYPE_HOLE_PRICE = 0.33;
    public static final String DONUT_STRING_DELIMITER = ":";

    // Coffee specific constants
    public static final int MAX_UNIQUE_ADDIN_COUNT = 5; // ex: maximum of 5 creams
    public static final int COFFEE_ADDIN_SPINNER_MIN_VALUE = 1; // lowest integer value the addin spinners can go to
    public static final double COFFEE_ADDIN_COST = 0.2;
    public static final double COFFEE_BASE_PRICE = 1.99;
    public static final double COFFEE_SIZE_SHORT_PRICE = COFFEE_BASE_PRICE;
    public static final double COFFEE_SIZE_TALL_PRICE = COFFEE_BASE_PRICE + 0.5;
    public static final double COFFEE_SIZE_GRANDE_PRICE = COFFEE_BASE_PRICE + 1.0;
    public static final double COFFEE_SIZE_VENTI_PRICE = COFFEE_BASE_PRICE + 1.5;
    public static final String COFFEE_STRING_DELIMITER = ":";
}