package cafe.controllers;

import cafe.models.Donut;
import cafe.models.Order;
import cafe.models.StoreOrders;
import cafe.utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller for OrderingDonuts FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class OrderingDonutsController {

    Donut currentDonutOrder;

    /**
     * Initialize the OrderingDonutsController. Called behind the scenes by JavaFX
     */
    @FXML
    protected void initialize() {
        // TODO initialize / do any preprocessing that is necessary
        currentDonutOrder = new Donut();
    }

    @FXML
    protected void addDonutToOrder() {
        Order.getInstance().add(currentDonutOrder);
    }
}