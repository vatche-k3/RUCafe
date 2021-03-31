package cafe.controllers;

import cafe.models.Order;
import cafe.models.MenuItem;
import javafx.fxml.FXML;

/**
 * Controller for CurrentOrderDetails FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class CurrentOrderDetailsController {

    private Order currentOrder;

    /**
     * Initialize the CurrentOrderDetails Controller. Called behind the scenes by JavaFX
     */
    @FXML
    protected void initialize() {
        // bind current order instance to local var
        currentOrder = Order.getInstance();

        for(MenuItem item : this.currentOrder.getItemsInOrder()) {
            System.out.println(item.toString());
        }
    }
}