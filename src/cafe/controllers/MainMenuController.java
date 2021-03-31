package cafe.controllers;

import cafe.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for MainMenu FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class MainMenuController {
    /**
     * Initialize the controller. Called by JavaFX behind the scenes
     */
    @FXML
    protected void initialize() {
        // TODO initialize / do any preprocessing that is necessary
    }

    /**
     * Called whenever "View Current Order Details" button is pressed.
     * Initializes a new stage and loads the Current Order Details View as a Modal
     */
    @FXML
    public void showCurrentOrderDetailsModal() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenuController.class.getResource("../guis/CurrentOrderDetails.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle(Constants.CURRENT_ORDER_DETAILS_WINDOW_TITLE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            // We will never get here, but required by JVM
        }
    }

    /**
     * Called whenever "Order Coffee" button is pressed.
     * Initializes a new stage and loads the OrderingCoffee View as a Modal
     */
    @FXML
    public void showOrderingCoffeeModal() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenuController.class.getResource("../guis/OrderingCoffee.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle(Constants.ORDERING_COFFEE_WINDOW_TITLE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            // We will never get here, but required by JVM
        }
    }

    /**
     * Called whenever "Order Donut" button is pressed.
     * Initializes a new stage and loads the Ordering Donuts View as a Modal
     */
    @FXML
    public void showOrderingDonutsModal() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenuController.class.getResource("../guis/OrderingDonuts.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle(Constants.ORDERING_DONUTS_WINDOW_TITLE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            // We will never get here, but required by JVM
        }
    }

    /**
     * Called whenever "View Store Orders" button is pressed.
     * Initializes a new stage and loads the Orders Page View as a Modal
     */
    @FXML
    public void showOrdersPageModal() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenuController.class.getResource("../guis/OrdersPage.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle(Constants.ORDERS_PAGE_WINDOW_TITLE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            // We will never get here, but required by JVM
        }
    }
}