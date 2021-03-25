package cafe.controllers;

import cafe.models.Coffee;
import cafe.models.Order;
import cafe.models.StoreOrders;
import cafe.utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Controller for OrderingCoffee FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class OrderingCoffeeController {

    // FXML references
    @FXML private TextField currentPrice;
    // Size radio buttons
    @FXML private RadioButton sizeIsShort;
    @FXML private RadioButton sizeIsTall;
    @FXML private RadioButton sizeIsGrande;
    @FXML private RadioButton sizeIsVenti;



    // Our current coffee order object
    private Coffee currentCoffeeOrder;

    /**
     * Initialize the OrderingCoffeeController. Called behind the scenes by JavaFX
     */
    @FXML
    protected void initialize() {
        // TODO initialize / do any preprocessing that is necessary
        currentCoffeeOrder = new Coffee();
    }


    /**
     * Adds the current Cofee to the current Order. Called by "Add to Order" button
     */
    @FXML
    protected void addCoffeeToOrder() {
        // TODO have button call this function
        Order.getInstance().add(currentCoffeeOrder);
    }


}