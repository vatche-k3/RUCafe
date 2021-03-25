package cafe.controllers;

import cafe.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

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
    public void showCurrentOrderDetails() {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainMenuController.class.getResource("../guis/CurrentOrderDetails.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle(Constants.CURRENT_ORDER_DETAILS_WINDOW_TITLE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            // We will never get here, but required by JVM
        }
    }
}