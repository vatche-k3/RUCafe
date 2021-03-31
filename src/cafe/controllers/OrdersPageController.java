package cafe.controllers;

import cafe.models.MenuItem;
import cafe.models.Order;
import cafe.models.StoreOrders;
import cafe.utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Controller for OrdersPage FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class OrdersPageController {

    /**
     * Initialize the OrdersPageController. Called behind the scenes by JavaFX
     */
    @FXML
    protected void initialize() {
        // TODO initialize / do any preprocessing that is necessary

        // TODO DEBUG REMOVEME
        System.out.println("REMOVEME LATER; DEBUG ONLY");
        ArrayList<Order> orders = StoreOrders.getInstance().getOrders();
        for(int i = 0; i < orders.size(); i++) {
            System.out.println("Order " + i);
            for(MenuItem item : orders.get(i).getItemsInOrder()) {
                System.out.println("\t" + item);
            }
        }
    }
}